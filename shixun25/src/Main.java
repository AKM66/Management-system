import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main{

    // 定义枚举类型，表示结果
    enum Result { LARGE, SMALL, LEOPARD }

    // 游戏逻辑封装到一个类中
    static class Game {
        private final int[] numbers;
        private Result systemResult;

        public Game() {
            // 使用 Stream API 生成 3 个随机数（0-6）
            this.numbers = IntStream.range(0, 3)
                    .map(i -> (int) (Math.random() * 7))
                    .toArray();
            determineResult();
        }

        // 判断系统结果
        private void determineResult() {
            int sum = Arrays.stream(numbers).sum();
            if (numbers[0] == numbers[1] && numbers[1] == numbers[2]) {
                systemResult = Result.LEOPARD; // 豹子
            } else if (sum > 9) {
                systemResult = Result.LARGE; // 大
            } else {
                systemResult = Result.SMALL; // 小
            }
        }

        // 获取随机生成的数字
        public int[] getNumbers() {
            return numbers;
        }

        // 获取系统结果
        public Result getSystemResult() {
            return systemResult;
        }

        // 判断用户是否猜中
        public boolean isUserWinner(Result userChoice) {
            return userChoice == systemResult;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Game game = new Game();

        // 输出随机生成的数字
        System.out.println("随机生成的数字为: " + Arrays.toString(game.getNumbers()));

        // 用户输入押宝选项
        System.out.println("请押宝[豹子/大/小]:");
        String input = sc.nextLine().trim();

        try {
            // 将用户输入转换为枚举类型
            Result userChoice = Result.valueOf(input.toUpperCase());

            // 判断用户是否猜中
            if (game.isUserWinner(userChoice)) {
                System.out.println("恭喜！猜中了！");
            } else {
                System.out.println("很遗憾，没猜中！系统结果是: " + game.getSystemResult());
            }
        } catch (IllegalArgumentException e) {
            System.err.println("非法输入！请输入“豹子”、“大”或“小”。");
        }
    }
}