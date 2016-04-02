import java.util.concurrent.ConcurrentHashMap;

public class Database {
ConcurrentHashMap<Integer, String> table;
	
	public Database() {
		// TODO Auto-generated constructor stub
		table = new ConcurrentHashMap<Integer, String>();
	}
	
	public String getList(){
		return table.elements().toString();
	}
	
	public String get(int askforid){
		String message = (String) table.get((askforid));
		return message;
	}
	
	public void delete(int givenid){
		table.remove(givenid);
		System.out.println("Item deleted.");
	}
	
	public void put(int givenid, String m){
		table.put(givenid, m);
	}
	public boolean post(int givenid, String m){
		String key = table.get(givenid);
		if(key != null){
			return false;
		}
		else
		{
			table.put(givenid, m);
			return true;
		}
	}
}
