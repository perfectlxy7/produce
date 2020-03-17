package pc;

/**
 * @param :$params$
 * @Description:
 * @Return: $returns$
 * @开发人员: 李雪滢
 * @单位：湖南农业大学
 * @Date: $date$ $time$
 * @开发版本：综合练习Vo.1
 */
public class pv {
    int semaphore=0;//信号量
     pv(int a){semaphore=a;}
     static int mutex=1;
      public synchronized void Wait(){ // synchronized 保证了此操作是一条原语,p操作，synchronized可以保证在同一时刻，只有一个线程可以执行某个方法或某个代码块
               semaphore--;//synchronized可以保证方法或者代码块在运行时，同一时刻只有一个方法可以进入到临界区
                if(semaphore<0){//小于0：阻塞的进程数目
                       try {         //抛出异常
                           this.wait();
                           } catch (InterruptedException e) {
                          e.printStackTrace();
                          }
                   }
     }
     public synchronized void Signal(){   // synchronized 保证了此操作是一条原语，v操作
               semaphore++;
              if(semaphore<=0){//如果有进程阻塞
                      this.notify();
                   }
     }

}
