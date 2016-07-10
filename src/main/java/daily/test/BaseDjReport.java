package daily.test;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.HorizontalBandAlignment;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

public class BaseDjReport {

	protected static final Log log = LogFactory.getLog(BaseDjReport.class);

	protected JasperPrint jp;
	protected JasperReport jr;
	protected DynamicReport dr;
	protected Map<String, Object> params = new HashMap<String, Object>();

	public void createReport() throws Exception {
		
		params.put("pageNumber",3);
		
		FastReportBuilder drb = new FastReportBuilder();

		String[] keys = new String[] { "日期", "昨天天气", "上周同期", "昨天夜间高峰在线", "上周同期高峰在线", "增幅", "7月4周同期平均高峰在线", "7月4周同期增幅",
				"昨天夜间高峰在线时长", "上周同期高峰在线时长" };
		// 可以通过 sql 语句获取
		String[] fileds = new String[] { "date_entered", "assigned_user_id", "billing_address_postalcode",
				"billing_address_postalcode", "billing_address_postalcode", "industry", "account_type", "account_type",
				"account_type", "account_type" };
		String dataSource = "accounts"; // 后续使用
		String sql = "select date_entered,assigned_user_id,billing_address_postalcode,"
				+ "billing_address_postalcode,billing_address_postalcode,industry,"
				+ "account_type,account_type,account_type,account_type from accounts where id like $P{id} limit 100";

		params.put("id", "%e");

		String title = "竞品城市司机报表";

		drb.setTitle(title);
		drb.setQuery(sql, DJConstants.QUERY_LANGUAGE_SQL);

		Color borderColor = new Color(218, 224, 231);

		Style headerStyle = new Style();
		Font textFont = new Font(12, "Microsoft YaHei", "YAHEI.TTF",
				Font.PDF_ENCODING_Identity_H_Unicode_with_horizontal_writing, true);
		headerStyle.setFont(textFont);
		headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		headerStyle.setBackgroundColor(Color.WHITE);
		headerStyle.setTransparency(Transparency.OPAQUE);
		headerStyle.setBorder(new Border(1, Border.BORDER_STYLE_SOLID, borderColor));
		Color headerColor = new Color(83, 91, 103);
		headerStyle.setTextColor(headerColor);

		Style titleStyle = new Style();
		Font titleFont = new Font(16, "Microsoft YaHei", "YAHEI.TTF",
				Font.PDF_ENCODING_Identity_H_Unicode_with_horizontal_writing, true);
		titleStyle.setFont(titleFont);
		titleStyle.setPaddingBottom(20);
		Color titleColor = new Color(83, 91, 103);

		titleStyle.setTextColor(titleColor);
		drb.setTitleStyle(titleStyle);

		Style detailStyle = new Style();
		detailStyle.setFont(textFont);
		detailStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		detailStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		detailStyle.setBackgroundColor(Color.WHITE);
		detailStyle.setTransparency(Transparency.OPAQUE);
		detailStyle.setBorder(new Border(1, Border.BORDER_STYLE_SOLID, borderColor));
		Color detailColor = new Color(122, 122, 122);
		detailStyle.setTextColor(detailColor);

		Style oddStyle = new Style();
		Color backgroundColor = new Color(241, 243, 247);
		oddStyle.setBackgroundColor(backgroundColor);

		for (int i = 0; i < keys.length; i++) {
			AbstractColumn column = ColumnBuilder.getNew().setColumnProperty(fileds[i], String.class.getName())
					.setTitle(keys[i]).setStyle(detailStyle).setHeaderStyle(headerStyle).build();
			drb.addColumn(column);
		}

		// 设置偶数行背景色
		drb.setPrintBackgroundOnOddRows(true).setOddRowBackgroundStyle(oddStyle);
		
		Style pageStyle = new StyleBuilder(true).build();
		Color pageColor = new Color(83, 91, 103);
		pageStyle.setTextColor(pageColor);
		
		String message = "<a href='www.baidu.com?pageNumber=\" + ($P{pageNumber}-1) + \"'>上一页</a> | "
				+ "<a href='www.baidu.com?pageNumber=\" + ($P{pageNumber}+1) + \"'>下一页</a>" ;
		
		AutoText autoText = new AutoText(message,AutoText.POSITION_FOOTER,HorizontalBandAlignment.CENTER);
		autoText.setStyle(pageStyle);
		drb.addAutoText(autoText);

		int columns = drb.getColumns().size();
		if (columns < 5) {
			drb.setPageSizeAndOrientation(Page.Page_Letter_Landscape());
		} else {
			drb.setPageSizeAndOrientation(Page.Page_Letter_Landscape());
		}

		drb.setUseFullPageWidth(true);

		dr = drb.build();

	}
	
	
	public void fillReport() throws Exception {
		
		jr = DynamicJasperHelper.generateJasperReport(dr, getLayoutManager(), params);
		jp = JasperFillManager.fillReport(jr, params);
		log.info("Filling done!");
	}

	protected LayoutManager getLayoutManager() {
		return new ClassicLayoutManager();
	}

	public static Connection createSQLConnection() throws Exception {
		Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sugarcrm", "root", "root");
		return con;
	}


	public void printViewer() throws Exception {
		Connection conn = createSQLConnection();
		jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), conn, params);
		JasperViewer.viewReport(jp);
	}
	
	protected void exportToPDF() throws Exception {
		Connection conn = createSQLConnection();
		jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), conn, params);
		ReportExporter.exportReport(jp, System.getProperty("user.dir") + "/target/reports/" + this.getClass().getName()
				+ ".pdf");
	}
	
	protected void exportToHtml() throws Exception {
		Connection conn = createSQLConnection();
		jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), conn, params);
		System.out.println(jp.getPages().size());
		ReportExporter.exportReportHtml(jp, System.getProperty("user.dir") + "/target/reports/" + this.getClass().getName()
				+ ".html");
	}

	protected void exportToJRXML() throws Exception {
		DynamicJasperHelper.generateJRXML(this.dr, this.getLayoutManager(), this.params, "UTF-8",
				System.getProperty("user.dir") + "/target/reports/" + this.getClass().getName() + ".jrxml");
	}


	public void compileJasper() throws Exception {
		String base = System.getProperty("user.dir") + "/target/reports/";
		String source = base + this.getClass().getName() + ".jrxml";
		String target = base + this.getClass().getName() + ".jasper";
		JasperCompileManager.compileReportToFile(source, target);
	}
	
	

	public static void main(String[] args) throws Exception {
		BaseDjReport baseDjReport = new BaseDjReport();
		baseDjReport.createReport();
		baseDjReport.fillReport();
		//baseDjReport.exportToJRXML();
		//baseDjReport.compileJasper();
		baseDjReport.exportToHtml();
		//baseDjReport.exportToPDF();
		//baseDjReport.printViewer();

	}

}
