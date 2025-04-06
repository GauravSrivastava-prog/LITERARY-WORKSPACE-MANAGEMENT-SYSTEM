abstract class Person {
    protected String name;

    Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void Menu();

    public abstract void performAction(int choice);
}
