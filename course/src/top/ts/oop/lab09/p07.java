package top.ts.oop.lab09;

public class p07 {
	public static void main(String[] args) {
		int i = 2;
		System.out.println(i);
		add(i);
		System.out.println(i);
		System.out.println("-----------------------");
		System.out.println(get0());
		System.out.println("-----------------------");
		System.out.println(get1());
		System.out.println("-----------------------");
		System.out.println(get2());
		System.out.println("-----------------------");
	}
	public static int add(int i) {
		i++;
		return i;
	}

	public static int get0(){
		int i=1;
		try{
			throw new Exception();
		}catch (Exception e){
			System.out.println("error");
			return i;
		}finally {
			i++;
			System.out.println("i in finally block:"+i);
		}
	}
	public static String get1(){
		String i="ok";
		try{
			throw new Exception();
		}catch (Exception e){
			System.out.println("error");
			return i;
		}finally {
			i+="finally";
			System.out.println("i in finally:"+i);
		}
	}
	public static StringBuilder get2(){
		StringBuilder i=new StringBuilder("ok");
		try{
			throw new Exception();
		}catch (Exception e){
			System.out.println("error");
			return i;
		}finally {
			i.append("finally");
			System.out.println("i in finally:"+i);
		}
	}
}