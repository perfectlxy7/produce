package pc;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 * @param :$params$
 * @Description:
 * @Return: $returns$
 * @开发人员: 李雪滢
 * @单位：湖南农业大学
 * @Date: $date$ $time$
 * @开发版本：综合练习Vo.1
 */
public class Main {
    public static void main(String[] args) {
        producer p=new producer();//实例化
        consumer c=new consumer();
        Thread pp=new Thread(p);
        Thread cc=new Thread(c);
        pp.start();
        cc.start();
    }

    static class defin{
        static pv empty=new pv(10);//定义信号量
        static pv full=new pv(0);
        static int buf[]=new int[10];//10个缓冲区

    }
    static class producer implements Runnable{
        @Override
        public void run() {
            int count=1;//物品count
            while (count<=20) {//生产20个后停止
                defin.empty.Wait();//减少一个空位
                int n = count % 10;//循环使用缓存区
                defin.buf[n] =count;
                System.out.println("生产者在缓冲区 "+n+" 中生产了物品 "+count);
                count++;
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                defin.full.Signal();//增加一个物品
            }
        }
    }

    static class consumer implements Runnable{
        @Override
        public void run() {
            int count=1;//物品
            while (count<=20) {//消费20个后停止
                defin.full.Wait();//消费一个物品
                int n = count % 10;//循环使用缓存区
                defin.buf[n] =count;
                System.out.println("消费者在缓冲区 "+n+" 中消费了物品 "+count);
                count++;
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                defin.empty.Signal();//增加一个空位
            }
        }
    }
}
