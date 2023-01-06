package vttp2022.paf.assessment.eshop.models;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class DispatchOrder {

    private String orderId;
    private String name;
	private String address;
	private String email;
    private String item;
	private Integer quantity;
    private String[] LineItem;

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }

	public String getAddress() { return this.address; }
	public void setAddress(String address) { this.address = address; }

	public String getEmail() { return this.email; }
	public void setEmail(String email) { this.email = email; }

    public String getItem() { return this.item; }
	public void setItem(String item) { this.item = item; }

	public Integer getQuantity() { return this.quantity; }
	public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String[] getLineItem() {
        return LineItem;
    }
    public void setLineItem(String[] lineItem) {
        this.LineItem = lineItem;
    }

    public static DispatchOrder create(Document d) {
        DispatchOrder dO = new DispatchOrder();
        List lineItemArr = (ArrayList) d.get("line_item");
        List newlineItemId = new LinkedList<>();
        for (Object a : lineItemArr) {
            ObjectId oa = (ObjectId) a;
            newlineItemId.add("item" + oa.toString());
        }

        dO.setOrderId(d.getString("order_id"));
        dO.setName(d.getString("name"));
        dO.setAddress(d.getString("address"));
        dO.setEmail(d.getString("email"));
        dO.setLineItem((String[]) newlineItemId.toArray(new String[newlineItemId.size()]));
        return dO;
    }

    public JsonObject toJSON() {
        JsonArray lineItemJ = null;
        JsonArrayBuilder bld = Json.createArrayBuilder();
        for (String x : getLineItem())
            bld.add(x);
            lineItemJ = bld.build();
        return Json.createObjectBuilder()
                .add("order_id", getOrderId())
                .add("name", getName())
                .add("address", getAddress())
                .add("email", getEmail())
                .add("line_item", lineItemJ.toString())
                .build();
    }

   

    }

