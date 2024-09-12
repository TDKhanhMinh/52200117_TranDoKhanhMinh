

public class Program {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Invalid expression");
            return;
        }

        int num1, num2;
        try {
            num1 = Integer.parseInt(args[0]);
            num2 = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid expression");
            return;
        }

        char operator = args[1].charAt(0);
        double result;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case 'x':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    System.out.println("Cannot divide by zero");
                    return;
                }
                result = (double) num1 / num2;
                break;
            case '^':
                result = Math.pow(num1, num2);
                break;
            default:
                System.out.println("Unsupported operator");
                return;
        }
        System.out.println(result);
    }
}


