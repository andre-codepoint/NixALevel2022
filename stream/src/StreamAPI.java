import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamAPI {
    public static String stringsToInt(List<String> strings) throws IllegalArgumentException {
        return strings.stream()
                .filter(s -> s != null)
                .filter(s -> s != "")
                .flatMapToInt(String::codePoints)
                .filter(Character::isDigit)
                .mapToObj(Character::toString)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Некорректні вхідні дані"));
    }

    public static BigDecimal findMostCommon(List<BigDecimal> list) {
        return list.stream()
                .filter(x -> x != null)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().max((o1, o2) -> o1.getValue().compareTo(o2.getValue()))
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new IllegalArgumentException("Некорректні вхідні дані"));
    }

    public static Map<LocalDate, List<LocalDateTime>> sortLocalDateTime(List<LocalDateTime> listLocalDateTime) throws IllegalArgumentException {
//        Map<LocalDate, List<LocalDateTime>> localDateTimeByDate = listLocalDateTime.stream().collect(Collectors.groupingBy(LocalDateTime::toLocalDate));
        return listLocalDateTime.stream()
                .filter(x -> x != null)
                .collect(Collectors.groupingBy(LocalDateTime::toLocalDate))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2) -> o1, LinkedHashMap::new));
    }

    public static void main(String[] args) {
//        Використовуючи Stream API, виконати наступні вправи:
//        1.Для списку рядків, відфільтрувати всі цифри, що зустрічаються у всіх рядках, і зібрати їх у загальне десяткове число. Наприклад:
//        Рядки [“string 1 text”, “2 string 3 text”, “45”] дадуть результат 12345
//        Підказка: Character має методи digit і isDigit, а String - codepoints().
        System.out.println("Task 1:   " + stringsToInt(Arrays.asList("sdf5rfdfv5gg7", null, "", "8df8cx8", "1xc.,_56")));

///        2.Для списку чисел (List<BigDecimal>) повернути те, що зустрічається найчастіше.
//         https://stackoverflow.com/questions/19031213/java-get-most-common-element-in-a-list
        System.out.println("Task 2:   " + findMostCommon(Arrays.asList(
                new BigDecimal(123),
                new BigDecimal(123),
                new BigDecimal(321),
                new BigDecimal(321),
                new BigDecimal(321))));

//        3.Для списку об'єктів типу LocalDateTime повернути асоціативний масив LocalDate -> List<LocalTime>,
//        згрупувавши дату/час за датою. Записи в map відсортувати по порядку дат.
//        https://stackoverflow.com/questions/64767228/sort-map-in-stream-by-localdatetime
        System.out.println("Task 3:   " + sortLocalDateTime(Arrays.asList(
                LocalDateTime.parse("2022-09-18T00:13:36"),
                LocalDateTime.parse("2022-05-13T00:17:20"),
                LocalDateTime.parse("2022-09-05T01:25:20"),
                LocalDateTime.parse("2022-09-15T00:17:20"),
                LocalDateTime.parse("2022-09-20T00:17:20"),
                LocalDateTime.parse("2022-09-18T00:13:33"),
                LocalDateTime.parse("2022-05-13T00:17:23"),
                LocalDateTime.parse("2022-09-05T01:25:23"),
                LocalDateTime.parse("2022-09-15T00:17:23"),
                LocalDateTime.parse("2022-09-20T00:17:23")
        )));
    }
}
