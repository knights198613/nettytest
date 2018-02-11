package com.jiangwei.nettytest.testone.anlyastic;

/**
 * Created by weijiang
 * Date: 2018/2/11
 * DESC:
 */
public class InToPost {
    private StackY stackY;
    private String outPut = "";



    public static void main(String[] args) {
        InToPost inToPost = new InToPost();
        String inPut = "A*(B+C)-D/(E+F)";
        String result = inToPost.doTransfer(inPut);

        System.out.println(result);
    }


    /**
     * 开始做中缀表达式转后缀表达式的任务
     */
    public String doTransfer(String inPutString) {
        initStack(inPutString.length());
        for(int j=0; j<inPutString.length(); j++) {
            char inPutChar = inPutString.charAt(j);
            this.stackY.displayStack("For "+inPutChar+" ");
            switch (inPutChar) {
                case '+':
                case '-':
                    goPopOper(inPutChar, 1);
                    break;
                case '*':
                case '/':
                    goPopOper(inPutChar, 2);
                    break;
                case '(':
                    stackY.push(inPutChar);
                    break;
                case ')':
                    goRightParen(inPutChar);
                    break;
                default:
                    this.outPut +=inPutChar;
                    break;
            }
        }

        while (!this.stackY.isEmpty()) {
            this.stackY.displayStack("While ");
            this.outPut+=this.stackY.pop();
        }

        this.stackY.displayStack("End  ");
        return this.outPut;
    }

    /**
     * 初始化用于盛装操作符的栈
     * @param size
     */
    public void initStack(int size) {
        stackY = new StackY(size);
    }

    /**
     *
     * @param operThis 操作符
     * @param pres     操作符优先级
     */
    public void goPopOper(char operThis, int pres) {
        while (!this.stackY.isEmpty()) {
            char popOper = this.stackY.pop();
            if(popOper == '(') {
                this.stackY.push(popOper);
                break;
            }else {
                int pres2;
                if(popOper == '+' || popOper == '-') {
                    pres2 = 1;
                }else {
                    pres2 = 2;
                }
                if(pres2 < pres) {
                   this.stackY.push(popOper);
                   break;
                }else {
                    this.outPut+=popOper;
                }
            }
        }
        this.stackY.push(operThis);
    }

    /**
     * 处理右边括号
     * @param rightParen
     */
    public void goRightParen(char rightParen) {
        while (!this.stackY.isEmpty()) {
            char popOper = this.stackY.pop();
            if(popOper == '(') {
                break;
            }else {
                this.outPut+=popOper;
            }
        }

    }
}
