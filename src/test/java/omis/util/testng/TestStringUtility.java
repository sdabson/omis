package omis.util.testng;

import org.testng.annotations.Test;

import omis.util.StringUtility;

@Test
public class TestStringUtility {
	@Test
	public void testIsNullOrEmpty_NotEmpty(){
		assert !StringUtility.isNullOrEmpty("Not empty");
	}
	
	@Test
	public void testIsNullOrEmpty_Empty(){
		assert StringUtility.isNullOrEmpty("");
	}
	
	@Test
	public void testIsNullOrEmpty_Null(){
		assert StringUtility.isNullOrEmpty(null);
	}
	
	final String testStringIsNullOrEmpty = "constant";
	@Test
	public void testIsNullOrEmpty_Constant(){
		assert !StringUtility.isNullOrEmpty(testStringIsNullOrEmpty);
	}
	
	@Test
	public void testAreNullOrEmpty_EmptyAndNullOnly(){
		assert StringUtility.areNullOrEmpty(null,"",null,"");
	}
	
	@Test
	public void testAreNullOrEmpty_EmptyAndNullPartial(){
		assert !StringUtility.areNullOrEmpty(null,"","aa","34");
	}
	
	@Test
	public void testAreNullOrEmpty_NoEmptyNoNull(){
		assert !StringUtility.areNullOrEmpty("#$","23","aa","cc");
	}
	
	@Test
	public void testAreNullOrEmpty_NoEmpty(){
		assert !StringUtility.areNullOrEmpty("#$",null,"aa",null);
	}
	
	@Test
	public void testAreNullOrEmpty_NoNull(){
		assert !StringUtility.areNullOrEmpty("","aa","","bb");
	}
	
	@Test
	public void testAreNullOrEmpty_AllNull(){
		assert StringUtility.areNullOrEmpty(null,null,null,null);
	}
	
	@Test
	public void testAreNullOrEmpty_AllEmpty(){
		assert StringUtility.areNullOrEmpty("","","","");
	}
	
	@Test
	public void testHasContent_HasContent(){
		assert StringUtility.hasContent("aa");
	}
	
	@Test
	public void testHasContent_ContentEmpty(){
		assert !StringUtility.hasContent("");
	}
	
	@Test
	public void testHasContent_ContentNull(){
		assert !StringUtility.hasContent(null);
	}
	
	final String testStringHasContent = "constant";
	@Test
	public void testHasContent_Constant(){
		assert StringUtility.hasContent(testStringHasContent);
	}
	
	@Test
	public void testHaveContent_EmptyAndNullOnly(){
		assert !StringUtility.haveContent(null,"",null,"");
	}
	
	@Test
	public void testHaveContent_EmptyAndNullPartial(){
		assert !StringUtility.haveContent(null,"","aa","34");
	}
	
	@Test
	public void testHaveContent_NoEmptyNoNull(){
		assert StringUtility.haveContent("#$","23","aa","cc");
	}
	
	@Test
	public void testHaveContent_NoEmpty(){
		assert !StringUtility.haveContent("#$",null,"aa",null);
	}
	
	@Test
	public void testHaveContent_NoNull(){
		assert !StringUtility.haveContent("","aa","","bb");
	}
	
	@Test
	public void testHaveContent_AllNull(){
		assert !StringUtility.haveContent(null,null,null,null);
	}
	
	@Test
	public void testHaveContent_AllEmpty(){
		assert !StringUtility.haveContent("","","","");
	}
	
	@Test
	public void testIsIntegral_test1(){
		assert StringUtility.isIntegral("12");
	}
	@Test
	public void testIsIntegral_test2(){
		assert StringUtility.isIntegral("34");
	}
	@Test
	public void testIsIntegral_test3(){
		assert !StringUtility.isIntegral("56.78");
	}
	@Test
	public void testIsIntegral_test4(){
		assert !StringUtility.isIntegral("9.1");
	}
	@Test
	public void testIsNumeric_test1(){
		assert StringUtility.isNumeric("12");
	}
	@Test
	public void testIsNumeric_test2(){
		assert StringUtility.isNumeric("34");
	}
	@Test
	public void testIsNumeric_test3(){
		assert StringUtility.isNumeric("56.78");
	}
	@Test
	public void testIsNumeric_test4(){
		assert StringUtility.isNumeric("9.1");
	}
	@Test
	public void testIsDecimal_test1(){
		assert !StringUtility.isDecimal("12");
	}
	@Test
	public void testIsDecimal_test2(){
		assert !StringUtility.isDecimal("34");
	}
	@Test
	public void testIsDecimal_test3(){
		assert StringUtility.isDecimal("56.78");
	}
	@Test
	public void testIsDecimal_test4(){
		assert StringUtility.isDecimal("9.1");
	}
}