import java.io.*;
class p4
{
    static void main()throws IOException
    {
        BufferedReader br=new BufferedReader(new FileReader("C:/Users/Anindya/Desktop/in4.txt"));
        int n=Integer.parseInt(br.readLine());
        PrintWriter pw=new PrintWriter(new FileWriter("C:/Users/Anindya/Desktop/out4.txt"));
        for(int z=0;z<n;z++)
        {
            String r="1",g="1",h="1";
            int a[]=new int[10];
            String p=br.readLine();
            qui:
            while(true)
            {
                String s=p;
                for(int j=0;j<10;j++)
                {
                    a[j]+=(s.charAt(0))-48;
                    s=s.substring(1);
                }
                int l=r.length();
                for(int k=0;k<l;k++)
                {
                    int e=r.charAt(0)-48;
                    r=r.substring(1);
                    a[e]--;
                }
                for(int i=0;i<10;i++)
                {
                    if(a[i]==(-1))
                        break qui;
                }
                g=h;r=increment(g);h=r;
            }
            pw.println(g.length());
        }
        pw.close();
    }
    static String increment(String s)
    {
        char x[]=s.toCharArray();
        char y[]=new char[x.length+1];
        System.arraycopy(x,0,y,1,x.length);
        y[y.length-1]++;y[0]=48;
        if(y[y.length-1]==':')
        for(int i=y.length-1;i>=0;i--)
        {
            if(y[i]==':')
            {
                y[i]=48;
                y[i-1]++;
            }
            if(y[i-1]==':')
                continue;
            else
                break;
        }
        String u=new String(y);
        if(u.charAt(0)==48)
            u=u.substring(1);
        return u;
    }
}