package Mkv;

public class rule {
	String a,b;
	rule(String a, String b )
	{
		this.a = a;
		this.b = b;
	}
	public String myReplaceFirst(String s, String a, String b)
	{
		int index = s.indexOf(a);
		if(index == -1)
			return s;
		String firstPart = s.substring(0, index);
		String secondPart = s.substring(index + a.length());
		s = firstPart + b + secondPart;
		return s;
		
	}
	public String applyRule(String s)
	{
		if(isTerminal())	
			s = myReplaceFirst(s, a, b.substring(1));
		else if(b.equals("null"))
				s = myReplaceFirst(s,a, "");
			 else
					s = myReplaceFirst(s,a, b);
		return s;
	}
	public boolean matches(String s)
	{
		if(s.contains(a))
			return true;
		return false;
	}
	public boolean isTerminal()
	{
		if(b.charAt(0) == '.')
			return true;
		return false;
	}
	public String getA()
	{
		return a;
	}
	public String getB()
	{
		return b;
	}
}
