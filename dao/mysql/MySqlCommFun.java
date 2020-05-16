package dao.mysql;

/**
 * MySQL common functionality
 *
 */
public class MySqlCommFun {
	public static final String SELECT = "select * from ";
	public static final String UPDATE = "update ";
	public static final String INSERT = "insert into ";
	public static final String NOT_NULL = " not null";
	public static final String CREATE_TABLE_INE = "create table if not exists ";
	public static final String DROP_TABLE_IE = "drop table if exists ";
	
	public static String varchar(int i) {
		return "varchar(" + i + ")";
	}
	
	public static String integer(int i) {
		return "int(" + i + ")";
	}
	
	public static String createTableType(String[] fieldNames, String[] fieldTypes) {
		if (fieldNames.length != fieldTypes.length) {
			throw new IllegalArgumentException("input parameters fieldNames and fieldTypes must be of same length.");
		}
		String str = fieldNames[0] + " " + fieldTypes[0];
		for (int i = 1; i < fieldNames.length; i++) {
			str += ", " + fieldNames[i] + " " + fieldTypes[i];
		}
		return str;
	}
}
