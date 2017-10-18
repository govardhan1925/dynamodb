package in.abilash.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DynamoDBLambda implements RequestHandler<String, String> {
	private DynamoDB dynamoDb;
	private String DYNAMODB_TABLE_NAME = "Employee";
//	private Regions REGION = Regions.US_WEST_2;

	public String handleRequest(String input, Context context) {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		dynamoDb = new DynamoDB(client);
		Table table = dynamoDb.getTable(DYNAMODB_TABLE_NAME);
		GetItemSpec spec = new GetItemSpec().withPrimaryKey("empId", 111);
		Item item = table.getItem(spec);
		System.out.println(item);
		return item.getString("Name");
	}
}
