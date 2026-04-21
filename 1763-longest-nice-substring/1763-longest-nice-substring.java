class Solution {
    public String longestNiceSubstring(String s) {
        String ans="";
        int maxlen=0;
        for(int i=0;i<s.length();i++){
            for(int j=i;j<s.length();j++){
                String sub=s.substring(i,j+1);
                if(niceString(s.substring(i,j+1))){
                    int len=sub.length();
                    if(len>maxlen){
                        maxlen=len;
                        ans=sub;
                    }
                }
                
            }
        }
        return ans;
    }
    public static boolean niceString(String s){
        HashSet<Character> set=new HashSet<>();
        int n=s.length();
        for(int i=0;i<n;i++){
            set.add(s.charAt(i));
        }
        for(int i=0;i<n;i++){
            if(Character.isLowerCase(s.charAt(i)) && !set.contains(Character.toUpperCase(s.charAt(i)))){
                return false;
            }
            if(Character.isUpperCase(s.charAt(i)) && !set.contains(Character.toLowerCase(s.charAt(i)))){
                return false;
            }
        }
        return true;
    }

}