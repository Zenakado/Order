package jdbcEx;

public class cal {
	
	public Order getOrder()
	{
		return new Order();
	}
	
	public static Order getOrder2(String name)
	{
		return new Order(name);
	}

}