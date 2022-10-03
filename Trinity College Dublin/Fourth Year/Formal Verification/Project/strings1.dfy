// Tumi
method isPrefix(pre: string, str: string) returns (res:bool)
	requires |pre| > 0 && |str| > 0;
{	
	if (|pre| <= |str|)	{
		if (pre == str[..|pre|])
			{return true; }
		else
			{return false; }
	}
	return false;
}
// Tumi
method isSubstring(sub: string, str: string) returns (res:bool)
	requires |sub| > 0 && |str| > 0;
{
	var i := 0;
	var diff := |str| - |sub|;
	var check : bool;

	while i <= diff {
		check := isPrefix(sub, str[i..]);

		if (check == true)
            {return true; }
        i := i+1;
	}
	return false;
}
// Christiana
method haveCommonKSubstring(k: nat, str1: string, str2: string) returns (found: bool) {

    if (k == 0) {
        return true;
	}
    
    if (|str1| > 0 && |str2| > 0) {

        var check := false;
        var i := 0;

        while (i+k <= |str1|) {

            check := isSubstring(str1[i..(i+k)], str2);
            if (check == true) {
                return true;
            }
            i := i+1;
        }
        return false;
    }
    return false;
}
// Christiana
method maxCommonSubstringLength(str1: string, str2: string) returns (len:nat) {

    if (|str1| > 0 && |str2| > 0) {

        var check := false;
        var length, maxLength := 0, 0;

        while (length <= |str1| && length <= |str2|) {

            check := haveCommonKSubstring(length, str1, str2);

            if (check == true) {
                maxLength := length;
                length := length + 1;
            }
            else {
                return maxLength;
            }
            return maxLength;
        }
    }
    return 0;
}

