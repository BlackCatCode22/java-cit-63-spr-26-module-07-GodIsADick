import java.io.*;
import java.util.*;

// Blake Wilson
// Zoo Keeper Challenge

public class ZooKeeperChallenge {

    // Helper function that removes spaces from the beginning and end of text.
    public static String trim(String text) {
        return text == null ? "" : text.trim();
    }

    // Helper function that copies a string and makes it all lowercase letters.
    public static String toLowerCopy(String text) {
        return text == null ? "" : text.toLowerCase();
    }

    // Helper function that splits a string every time it sees a comma.
    public static List<String> splitByComma(String line) {
        List<String> pieces = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ',') {
                pieces.add(trim(current.toString()));
                current.setLength(0);
            } else {
                current.append(line.charAt(i));
            }
        }

        pieces.add(trim(current.toString()));
        return pieces;
    }

    // Helper function that turns a season word into a month and day.
    public static String pickSeasonDate(String seasonWord) {
        String season = toLowerCopy(seasonWord);

        if (season.equals("spring")) {
            return "03-15";
        }
        if (season.equals("summer")) {
            return "06-15";
        }
        if (season.equals("fall") || season.equals("autumn")) {
            return "09-15";
        }
        if (season.equals("winter")) {
            return "12-15";
        }

        return "06-15";
    }

    // Helper function that creates a birthday string using age and season.
    public static String buildBirthDate(int age, String season, int arrivalYear) {
        int birthYear = arrivalYear - age;
        return birthYear + "-" + pickSeasonDate(season);
    }

    // Helper function that builds ID strings like Hy01 or Li03.
    public static String buildId(String species, int number) {
        String lower = toLowerCopy(species);
        StringBuilder prefix = new StringBuilder();

        if (!lower.isEmpty()) {
            prefix.append(Character.toUpperCase(lower.charAt(0)));
        }

        if (lower.length() > 1) {
            prefix.append(lower.charAt(1));
        } else {
            prefix.append('x');
        }

        if (number < 10) {
            return prefix + "0" + number;
        }

        return prefix + String.valueOf(number);
    }

    // Base Animal class that stores shared information for all animals.
    public static abstract class Animal {
        private String name;
        private int age;
        private String species;
        private String sex;
        private String color;
        private int weight;
        private String origin;
        private String birthDate;
        private String arrivalDate;
        private String id;

        public Animal(String newName,
                int newAge,
                String newSpecies,
                String newSex,
                String newColor,
                int newWeight,
                String newOrigin,
                String newBirthDate,
                String newArrivalDate,
                String newId) {
            this.name = newName;
            this.age = newAge;
            this.species = newSpecies;
            this.sex = newSex;
            this.color = newColor;
            this.weight = newWeight;
            this.origin = newOrigin;
            this.birthDate = newBirthDate;
            this.arrivalDate = newArrivalDate;
            this.id = newId;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getSpecies() {
            return species;
        }

        public String getSex() {
            return sex;
        }

        public String getColor() {
            return color;
        }

        public int getWeight() {
            return weight;
        }

        public String getOrigin() {
            return origin;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public String getArrivalDate() {
            return arrivalDate;
        }

        public String getId() {
            return id;
        }

        public abstract String getHabitatTitle();
    }

    // Hyena subclass that sets the species name and habitat label.
    public static class Hyena extends Animal {
        public Hyena(String newName,
                int newAge,
                String newSex,
                String newColor,
                int newWeight,
                String newOrigin,
                String newBirthDate,
                String newArrivalDate,
                String newId) {
            super(newName, newAge, "Hyena", newSex, newColor, newWeight, newOrigin, newBirthDate, newArrivalDate,
                    newId);
        }

        @Override
        public String getHabitatTitle() {
            return "Hyena Habitat:";
        }
    }

    // Lion subclass that provides the lion habitat information.
    public static class Lion extends Animal {
        public Lion(String newName,
                int newAge,
                String newSex,
                String newColor,
                int newWeight,
                String newOrigin,
                String newBirthDate,
                String newArrivalDate,
                String newId) {
            super(newName, newAge, "Lion", newSex, newColor, newWeight, newOrigin, newBirthDate, newArrivalDate, newId);
        }

        @Override
        public String getHabitatTitle() {
            return "Lion Habitat:";
        }
    }

    // Tiger subclass that provides the tiger habitat information.
    public static class Tiger extends Animal {
        public Tiger(String newName,
                int newAge,
                String newSex,
                String newColor,
                int newWeight,
                String newOrigin,
                String newBirthDate,
                String newArrivalDate,
                String newId) {
            super(newName, newAge, "Tiger", newSex, newColor, newWeight, newOrigin, newBirthDate, newArrivalDate,
                    newId);
        }

        @Override
        public String getHabitatTitle() {
            return "Tiger Habitat:";
        }
    }

    // Bear subclass that provides the bear habitat information.
    public static class Bear extends Animal {
        public Bear(String newName,
                int newAge,
                String newSex,
                String newColor,
                int newWeight,
                String newOrigin,
                String newBirthDate,
                String newArrivalDate,
                String newId) {
            super(newName, newAge, "Bear", newSex, newColor, newWeight, newOrigin, newBirthDate, newArrivalDate, newId);
        }

        @Override
        public String getHabitatTitle() {
            return "Bear Habitat:";
        }
    }

    // Function that reads animal names from a file and stores them by species.
    public static Map<String, List<String>> readNames(String fileName) {
        Map<String, List<String>> names = new HashMap<>();

        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            String line;
            String currentSpecies = "";

            while ((line = input.readLine()) != null) {
                String trimmed = trim(line);

                if (trimmed.isEmpty()) {
                    continue;
                }

                String lowered = toLowerCopy(trimmed);

                if (lowered.contains("hyena") && lowered.contains("names")) {
                    currentSpecies = "hyena";
                    names.putIfAbsent(currentSpecies, new ArrayList<>());
                    continue;
                }
                if (lowered.contains("lion") && lowered.contains("names")) {
                    currentSpecies = "lion";
                    names.putIfAbsent(currentSpecies, new ArrayList<>());
                    continue;
                }
                if (lowered.contains("tiger") && lowered.contains("names")) {
                    currentSpecies = "tiger";
                    names.putIfAbsent(currentSpecies, new ArrayList<>());
                    continue;
                }
                if (lowered.contains("bear") && lowered.contains("names")) {
                    currentSpecies = "bear";
                    names.putIfAbsent(currentSpecies, new ArrayList<>());
                    continue;
                }

                if (!currentSpecies.isEmpty()) {
                    List<String> tokens = splitByComma(trimmed);
                    for (String token : tokens) {
                        if (!token.isEmpty()) {
                            names.get(currentSpecies).add(token);
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Could not open " + fileName + " for reading.");
        }

        return names;
    }

    // Function that hands back the next name for a given species.
    public static String getNextName(String species,
            Map<String, List<String>> names,
            Map<String, Integer> nameIndex) {
        String key = toLowerCopy(species);

        List<String> speciesNames = names.getOrDefault(key, new ArrayList<>());
        if (speciesNames.isEmpty()) {
            return "Unnamed";
        }

        int index = nameIndex.getOrDefault(key, 0);
        if (index >= speciesNames.size()) {
            return "Unnamed";
        }

        nameIndex.put(key, index + 1);
        return speciesNames.get(index);
    }

    // Function that builds a single animal object from one line of text.
    public static Animal buildAnimalFromLine(String line,
            Map<String, List<String>> names,
            Map<String, Integer> nameIndex,
            Map<String, Integer> idNumbers,
            String arrivalDate,
            int arrivalYear) {

        int fromSpot = line.indexOf(", from ");
        String location = "";
        String mainPart = line;

        if (fromSpot != -1) {
            location = trim(line.substring(fromSpot + 7));
            mainPart = line.substring(0, fromSpot);
        }

        List<String> pieces = splitByComma(mainPart);
        if (pieces.size() < 4) {
            return null;
        }

        int age = 0;
        String sex = "";
        String species = "";

        Scanner firstScanner = new Scanner(pieces.get(0));
        if (firstScanner.hasNextInt()) {
            age = firstScanner.nextInt();
        }
        if (firstScanner.hasNext())
            firstScanner.next(); // year
        if (firstScanner.hasNext())
            firstScanner.next(); // old
        if (firstScanner.hasNext())
            sex = firstScanner.next();
        if (firstScanner.hasNext())
            species = firstScanner.next();
        firstScanner.close();

        String season = pieces.get(1);
        String loweredSeason = toLowerCopy(season);

        if (loweredSeason.contains("born in")) {
            Scanner seasonScanner = new Scanner(season);
            if (seasonScanner.hasNext())
                seasonScanner.next(); // born
            if (seasonScanner.hasNext())
                seasonScanner.next(); // in
            if (seasonScanner.hasNext()) {
                season = seasonScanner.next();
            }
            seasonScanner.close();
        } else {
            season = "unknown";
        }

        String color = pieces.get(2);

        int weight = 0;
        Scanner weightScanner = new Scanner(pieces.get(3));
        if (weightScanner.hasNextInt()) {
            weight = weightScanner.nextInt();
        }
        weightScanner.close();

        String name = getNextName(species, names, nameIndex);

        String lowerSpecies = toLowerCopy(species);
        int idNumber = idNumbers.getOrDefault(lowerSpecies, 0) + 1;
        idNumbers.put(lowerSpecies, idNumber);

        String id = buildId(species, idNumber);
        String birthDate = buildBirthDate(age, season, arrivalYear);

        switch (lowerSpecies) {
            case "hyena":
                return new Hyena(name, age, sex, color, weight, location, birthDate, arrivalDate, id);
            case "lion":
                return new Lion(name, age, sex, color, weight, location, birthDate, arrivalDate, id);
            case "tiger":
                return new Tiger(name, age, sex, color, weight, location, birthDate, arrivalDate, id);
            case "bear":
                return new Bear(name, age, sex, color, weight, location, birthDate, arrivalDate, id);
            default:
                return null;
        }
    }

    // Function that writes the final report grouped by habitat and totals.
    public static void writeReport(String fileName,
            List<Animal> animals,
            Map<String, Integer> speciesCounts) {

        try (PrintWriter output = new PrintWriter(new FileWriter(fileName))) {

            Map<String, List<Animal>> habitats = new TreeMap<>();

            for (Animal animal : animals) {
                habitats.putIfAbsent(animal.getHabitatTitle(), new ArrayList<>());
                habitats.get(animal.getHabitatTitle()).add(animal);
            }

            for (Map.Entry<String, List<Animal>> entry : habitats.entrySet()) {
                output.println(entry.getKey());

                for (Animal animal : entry.getValue()) {
                    output.println(
                            animal.getId() + "; " +
                                    animal.getName() + "; age " + animal.getAge() + "; birth date "
                                    + animal.getBirthDate() + "; " +
                                    animal.getColor() + "; " + animal.getSex() + "; " +
                                    animal.getWeight() + " pounds; from " + animal.getOrigin() +
                                    "; arrived " + animal.getArrivalDate());
                }

                String lowerTitle = toLowerCopy(entry.getKey());

                if (lowerTitle.contains("hyena")) {
                    output.println("Total Hyena count: " + speciesCounts.getOrDefault("hyena", 0));
                } else if (lowerTitle.contains("lion")) {
                    output.println("Total Lion count: " + speciesCounts.getOrDefault("lion", 0));
                } else if (lowerTitle.contains("tiger")) {
                    output.println("Total Tiger count: " + speciesCounts.getOrDefault("tiger", 0));
                } else if (lowerTitle.contains("bear")) {
                    output.println("Total Bear count: " + speciesCounts.getOrDefault("bear", 0));
                }

                output.println();
            }

            output.println("Total animals in zoo: " + animals.size());

        } catch (IOException e) {
            System.out.println("Could not open " + fileName + " for writing.");
        }
    }

    // Main function that coordinates loading files, building animals, and
    // reporting.
    public static void main(String[] args) {
        final String arrivalDate = "2025-11-17";
        final int arrivalYear = 2025;

        Map<String, List<String>> names = readNames("animalNames.txt");
        if (names.isEmpty()) {
            return;
        }

        Map<String, Integer> nameIndex = new HashMap<>();
        Map<String, Integer> idNumbers = new HashMap<>();
        Map<String, Integer> speciesCounts = new HashMap<>();

        List<Animal> animals = new ArrayList<>();

        try (BufferedReader arrivals = new BufferedReader(new FileReader("arrivingAnimals.txt"))) {
            String line;

            while ((line = arrivals.readLine()) != null) {
                String trimmed = trim(line);

                if (trimmed.isEmpty()) {
                    continue;
                }

                Animal animal = buildAnimalFromLine(trimmed, names, nameIndex, idNumbers, arrivalDate, arrivalYear);

                if (animal != null) {
                    animals.add(animal);
                    String lowerSpecies = toLowerCopy(animal.getSpecies());
                    speciesCounts.put(lowerSpecies, speciesCounts.getOrDefault(lowerSpecies, 0) + 1);
                }
            }

        } catch (IOException e) {
            System.out.println("Could not open arrivingAnimals.txt for reading.");
            return;
        }

        writeReport("zooPopulation.txt", animals, speciesCounts);

        System.out.println("Zoo population report created successfully.");
    }
}