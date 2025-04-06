package pers.lionlinzq.algo;



public class Main {
    public static void main(String[] args) {
        convert("[[0,1],[1,2],[1,3],[1,4],[0,5],[5,6],[6,7],[7,8],[0,9],[9,10],[9,12],[10,11]]");
    }

    public static String convertPassword(String password){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < password.length(); i++){
            char c = password.charAt(i);
            if (c >= 'B' && c <= 'Z'){
                c = (char) (c - 1);
                c = Character.toLowerCase(c);
            }else if (c == 'A'){
                c = 'z';
            }
            if (c >= 'a' && c <= 'c'){
                stringBuilder.append(2);
            }else if (c >= 'd' && c <= 'f'){
                stringBuilder.append(3);
            }else if (c >= 'g' && c <= 'i'){
                stringBuilder.append(4);
            }else if (c >= 'j' && c <= 'l'){
                stringBuilder.append(5);
            } else if (c >= 'm' && c <= 'o'){
                stringBuilder.append(6);
            } else if (c >= 'p' && c <= 's'){
                stringBuilder.append(7);
            } else if (c >= 't' && c <= 'v'){
                stringBuilder.append(8);
            }  else if (c >= 'w' && c <= 'z'){
                stringBuilder.append(9);
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    public static void convert(String string) {
        // "[[0,1],[1,2],[1,3],[1,4],[0,5],[5,6],[6,7],[7,8],[0,9],[9,10],[9,12],[10,11]]",解析生成对应的数组
    }
}
