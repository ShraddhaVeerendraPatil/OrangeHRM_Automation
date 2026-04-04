package com.orangeHRM.test;

public class reverseAString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		String str="Shraddha";
		String Rev="";
		
		for (int i=str.length()-1;i>=0;i--) {
			Rev=Rev+str.charAt(i);
		}
		System.out.println("reverese string is :"+Rev);
*/
		
		String sentence="This is Ram";
		String []words=sentence.split(" ");
		String res="";
		
		for (int i=0;i<words.length;i++) {
			String word=words[i];
			String rev="";
		
			for (int j=word.length()-1;j>=0;j--) {
				rev=rev+word.charAt(j);
			}
			res=res+rev+" ";

			
		}System.out.println("Reveresed string :"+res);
	}

}
