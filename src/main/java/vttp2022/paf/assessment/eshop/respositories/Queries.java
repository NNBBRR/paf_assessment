package vttp2022.paf.assessment.eshop.respositories;

public class Queries {

    public static String SQL_SELECT_CUSTOMERS_BY_NAMES = "SELECT name, address, email FROM customers WHERE name = ?";
    
    public static String SQL_SELECT_CUSTOMERS_BY_NAME = "SELECT name FROM customers WHERE name = ?";

    public static String SQL_INSERT_PURCHASE_ORDER = "insert into order(order_id, name, order_date) values (?, ?, SYSDATE())";
    
    public static String SQL_INSERT_LINE_ITEM = "insert into line_item(item, quantity, order_id) values (?, ?, ?)";
}
