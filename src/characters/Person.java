package characters;

public class Person implements Comparable<Person> {
	
	String name;
	int score;
	
	public Person(String name, int score){
		this.name = name;
		this.score = score;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getScore() {
		return this.score;
	}
	
	@Override
	public String toString() {
		return "name: " + this.name + "\n score: " + this.score;
	}
	
	@Override
	public int compareTo(Person other) {
		int result = (int) ((other.getScore() - this.getScore()) * 1000);
		return result;
	}

}
