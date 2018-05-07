package codility;

import java.time.Duration;
import java.util.*;

public class Question2 {

    private static final String LINE_END = "\n";
    private static final String CALL_PART_SEPARATOR = ",";
    private static final String TIME_SEPARATOR = ":";

    private final NavigableMap<PhoneKey, PhoneNumber> phoneNumbersByDuration = new TreeMap<>();
    private final Map<String, PhoneNumber> phoneNumbers = new HashMap<>();

    public int solution(String S) {
        final String[] phoneCalls = S.split(LINE_END);
        for (String call : phoneCalls) {
            final PhoneNumber currentCall = createNumberFromString(call);
            final PhoneNumber existingTotal = phoneNumbers.get(currentCall.key.number);

            if (existingTotal == null) {
                phoneNumbers.put(currentCall.key.number, currentCall);
                phoneNumbersByDuration.put(currentCall.key, currentCall);
            } else {
                final PhoneNumber updatedPhoneNumber = updateRunningTotal(currentCall, existingTotal);

                phoneNumbers.put(updatedPhoneNumber.key.number, updatedPhoneNumber);
                phoneNumbersByDuration.remove(existingTotal.key);
                phoneNumbersByDuration.put(updatedPhoneNumber.key, updatedPhoneNumber);
            }
        }
        return (int)totalCost();
    }

    private PhoneNumber updateRunningTotal(PhoneNumber first, PhoneNumber second) {
        return new PhoneNumber(
                new PhoneKey(
                        first.key.number,
                        first.key.duration + second.key.duration
                ),
                first.runningCost + second.runningCost
        );
    }

    private PhoneNumber createNumberFromString(String call) {
        final String[] callParts = call.split(CALL_PART_SEPARATOR);

        final Duration duration = durationFromString(callParts[0]);
        final String number = callParts[1];
        final long costInCents = calculateCost(duration);

        return new PhoneNumber(new PhoneKey(number, duration.toMillis()), costInCents);
    }

    private Duration durationFromString(String timeString) {
        final String[] timeParts = timeString.split(TIME_SEPARATOR);

        final String hours = timeParts[0];
        final String minutes = timeParts[1];
        final String seconds = timeParts[2];

        return Duration.ofHours(Long.valueOf(hours))
                .plusMinutes(Long.valueOf(minutes))
                .plusSeconds(Long.valueOf(seconds));
    }

    private long calculateCost(Duration duration) {
        if (duration.toMinutes() < 5) {
            return duration.toMillis() / 1000 * 3;
        }
        if (duration.toMillis() % 60 != 0) {
            return duration.plusMinutes(1).toMinutes() * 150;
        }
        return duration.toMinutes() * 150;
    }

    private long totalCost() {
        //Remove the phone number with the long duration - its free
        phoneNumbersByDuration.remove(phoneNumbersByDuration.lastKey());

        long totalCost = 0;
        for (Map.Entry<PhoneKey, PhoneNumber> entry : phoneNumbersByDuration.entrySet()) {
            totalCost += entry.getValue().runningCost;
        }
        return totalCost;
    }

    public class PhoneNumber {
        private final PhoneKey key;
        private final long runningCost;

        private PhoneNumber(PhoneKey key, long runningCost) {
            this.key = key;
            this.runningCost = runningCost;
        }
    }

    public class PhoneKey implements Comparable<PhoneKey> {

        private final String number;
        private final long duration;

        public PhoneKey(String number, long duration) {
            this.number = number;
            this.duration = duration;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PhoneKey phoneKey = (PhoneKey) o;
            return duration == phoneKey.duration &&
                    Objects.equals(number, phoneKey.number);
        }

        @Override
        public int hashCode() {
            return Objects.hash(number, duration);
        }

        @Override
        public int compareTo(PhoneKey o) {
            int comparison = Long.compare(duration, o.duration);
            if (comparison == 0) {
                return number.compareTo(o.number);
            }
            return comparison;
        }
    }

    public static void main(String[] args) {
        Question2 q = new Question2();
        System.out.println(q.solution("00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090"));
    }
}
