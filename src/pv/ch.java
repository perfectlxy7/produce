package pv;

import pc.pv;

/**
 * @param :$params$
 * @Description:
 * @Return: $returns$
 * @开发人员: 李雪滢
 * @单位：湖南农业大学
 * @Date: $date$ $time$
 * @开发版本：综合练习Vo.1
 */
public class ch {
    int semaphore=0;//信号量
    ch(){}
    ch(int a){semaphore=a;}
    public  void p(ch x){
        int pp;
        while(x.semaphore<=0)
        {
            System.out.println("please wait!\n");
            pp=123456789;
            pp--;
            while(pp>0);
        }
    x.semaphore=(x.semaphore)-1;
    }
    public  void v(ch x) {
        x.semaphore=x.semaphore+1;
    }
}
