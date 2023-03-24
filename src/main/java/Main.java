
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Long count = persons.stream()
                .filter(p -> p.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + count + " чел.");
        System.out.println();

        List<String> conscripts = persons.stream()
                .filter(p -> p.getAge() >= 18 && p.getAge() <= 27)
                .filter(p -> p.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println("Список призывников: " + conscripts);
        System.out.println();

        List <Person> workingAgePersons = persons.stream()
                .filter(p -> (p.getAge() >= 18 && p.getAge() <= 65 && p.getSex() == Sex.MAN) || (p.getAge() >= 18 && p.getAge() <= 60 && p.getSex() == Sex.WOMAN))
                .filter(p -> p.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .limit(5)
                .collect(Collectors.toList());
        System.out.println("Список работоспособных людей с высшим образованием:");
        System.out.println(workingAgePersons);
    }
}