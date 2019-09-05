
public class SCHashTable implements HashTable {
	
	HashNode[] hash = new HashNode[11]; //creates an array of hashnodes
	
	public SCHashTable() {
		//creates empty array
		for(int i=0; i<hash.length; i++) {
			hash[i] = null;
		}
	}

	public int hashFunction(String key) {
		if(key == null) {
			throw new NullPointerException();
		}
		int keyNum = Math.abs(key.hashCode());
		return keyNum % hash.length;
		
	}

	
	public void put(String key, int value) {
		HashNode node = new HashNode(key, value);
		//getting index for new node
		int index = hashFunction(key);
		//first check for collision
		HashNode temp = hash[index];
		if(temp!=null) {
			if(temp.key.equals(key)) {
				temp.value = value;
				return;
			}
			while(temp.next!=null) {
				if(temp.key == key) {
					temp.value = value;
					return;
				}
				temp = temp.next;
			}
			temp.next = node;
		}
		else {
			hash[index] = node;
			node.next = null;
		}
		
		
	}

	public boolean removeKey(String key) {
		boolean bool = false; //boolean intializer
		int index = hashFunction(key);
		HashNode temp = hash[index];
		
		if(temp == null) {
			return bool;
		}
		else if(temp.key.contentEquals(key)) {
			hash[index]=temp.next;
			bool = true;
			return bool;
		}

		while(temp!=null) {
			if(temp.next == null) {
				return bool;
			}
			if(temp.next.key ==key) {
			temp.next = temp.next.next;
			bool = true;
			return bool;
			}
			temp = temp.next;
		}
		
		return bool;
	}

	public boolean containsKey(String key) {
		boolean bool = false; //boolean initializer
		int index = hashFunction(key);
		HashNode temp = hash[index];
		while(temp!=null) {
			if(temp.key.equals(key)) {
				bool = true;
				return bool;
			}
			temp = temp.next;
		}
		return bool;
	}

	public int get(String key) {
		int index = hashFunction(key);
		int val = 0;
		HashNode temp = hash[index];
		if(!this.containsKey(key)) {
			return 0;
		}
		while(temp!=null) {
			if(temp.key.equals(key)) {
				val = temp.value;
				return val;
			}
			temp = temp.next;
		}
		
		return val;
	}
	
	public String toString() {
		String str ="index (key,value)"; //string init
		for(int i =0; i< hash.length; i++) {
			if(Integer.toString(i).length() == 1) {
			str = str + System.lineSeparator()+ i +".....";
			}
			else {
				str = str + System.lineSeparator() + i + "....";
			}
			HashNode temp = hash[i];
			while(temp!=null) {
				if(temp.next == null) {
					str = str + "("+temp.key+","+temp.value+")";
				}
				else {
					str = str + "("+temp.key+","+temp.value+")"+"->";
				}
				temp = temp.next;
			}
		}

		return str;
	}

}
