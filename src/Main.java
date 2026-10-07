void main() {


    //Liczba ma wiecej cyfr nieparzystych niz parzystych. Zwroc liste takich liczb

    List<Integer> numbers = new ArrayList<>(Arrays.asList(112, 2992, 2223, 1117, null));
    System.out.println(evenNumbs(numbers));
}

public List<Integer> evenNumbs(List<Integer> numbers) {
    List<Integer> result = new ArrayList<>();
    for (Integer x : numbers) {
        if (x == null) {
            break;
        }
        int even = 0;
        int odd = 0;
        int number = x;
        while (x > 0) {
            int digit = x % 10;
            if (digit % 2 != 0) {
                even++;
            } else  {
                odd++;
            }
            x /= 10;
        }
        if (even > odd) {
            result.add(number);
        }
    }
    return result;
}