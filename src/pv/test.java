package pv;

/**
 * @param :$params$
 * @Description:
 * @Return: $returns$
 * @开发人员: 李雪滢
 * @单位：湖南农业大学
 * @Date: $date$ $time$
 * @开发版本：综合练习Vo.1
 */
public class test {
    static ch mutex=new ch(1);
    static ch empty=new ch(5);//定义信号量
    static ch full=new ch(5);
    static int buf[]=new int[10];//10个缓冲区
    static ch front=new ch(0);
    static ch rear=new ch(0);
    void produce_item(int item_ptr)
    {
        /*printf("produce an item\n");*/
    item_ptr='m';		/* 'm' is "man满" */
    }
    void enter_item(int x){
       front.semaphore=(front.semaphore+1)%10;
        buf[front.semaphore]=x;
        System.out.println("enter_item "+buf[front.semaphore]+"to buf["+front.semaphore+"]");
    }
    void remove_item(int y){
        rear.semaphore=(rear.semaphore+1)%10;
        System.out.println("remove_item "+buf[rear.semaphore]+"from buf["+rear.semaphore+"]");
        y= buf[rear.semaphore];
        buf[rear.semaphore]='k';
        System.out.println(" so the buf["+rear.semaphore+"] changed to empty--"+buf[rear.semaphore]);
    }
    void consume_item(int y)
    {
        System.out.println("cosume the item :the screem print"+y);//("cosume the item :the screem print %c\n", y);
    }
    static class pro implements Runnable{
    int item=0;
        @Override
        public void run() {
    while (true){
        test xx=new test();
        xx.produce_item(item);
        ch pp=new ch();
       pp.p(empty);
       pp.p(mutex);
        xx.enter_item(item);
        pp.v(mutex);
        pp.v(full);
      /*  try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
        }
    }
    static class con implements Runnable{
        @Override
        public void run() {
            int get_item = 0;
            while (true){
                test xx=new test();
                ch cc=new ch();
                cc.p(full);
                cc.p(mutex);
                xx.remove_item(get_item);
                cc.v(mutex);
                cc.v(empty);
                System.out.println("cosume the item :the screem print");
              /*  try {
                    wait(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }
    }
    public static void main(String[] args) {
        pro p=new pro();//实例化
        con c=new con();
        System.out.println("1kais");
        Thread pp=new Thread(p);

        Thread cc=new Thread(c);
        cc.start();
        pp.start();
        System.out.println("2kais");

    }
}
