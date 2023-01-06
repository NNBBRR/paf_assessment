package vttp2022.paf.assessment.eshop.respositories;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AddFieldsOperation;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.AddFieldsOperation.AddFieldsOperationBuilder;

import vttp2022.paf.assessment.eshop.models.DispatchOrder;

public class DispatchRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<DispatchOrder> aggregateOrder() {
        
        GroupOperation grpByOrderId = Aggregation
                .group("orderId")
                .push("orderId")
                .as("orderId");
        
        ProjectionOperation projection = Aggregation
                .project("order_id", "name", "address", "email")

                .and("lineItems").as("lineItems");
        
                AddFieldsOperationBuilder adFieldOpsBld = Aggregation.addFields();
        adFieldOpsBld.addFieldWithValue("createdBy", "Ng Bing Rong");
        AddFieldsOperation newFieldOps = adFieldOpsBld.build();

        Aggregation pipeline = Aggregation
                .newAggregation(grpByOrderId, projection, newFieldOps);
        AggregationResults<Document> results = mongoTemplate
                .aggregate(pipeline, "order",
                        Document.class);

        List<DispatchOrder> arrgArr = new LinkedList<DispatchOrder>();
        Iterator<Document> cursor = results.iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();      //fetch data
            arrgArr.add(DispatchOrder.create(doc)); //set to array List
        }
        return arrgArr;
    }
    
}
