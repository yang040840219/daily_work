package com.machine;

public class BuildModel {
	
	public static double lat_start = 22.403306;
	public static double lng_start = 113.764437;
	public static double lat_step = 0.009053;
	public static double lng_step = 0.009766;
	public static int all_num = 4539;
	public static int grid_lat_num = 51;
	public static int lng_num = 89;

	public static int getCityGridId(int city_id, double lat, double lng) {
		@SuppressWarnings("rawtypes")
		double lat1 = lat_start;
		double lng1 = lng_start;

		int lat_num_temp = (int) (Math.ceil((lat - lat1) / lat_step));
		int lng_num_temp = (int) (Math.ceil((lng - lng1) / lng_step));

		int grid_id = (lat_num_temp - 1) * lng_num + lng_num_temp;
		int gridid = grid_id;
		if (gridid < 1 || gridid > all_num)
			gridid = -1;

		return gridid;
	}
}
