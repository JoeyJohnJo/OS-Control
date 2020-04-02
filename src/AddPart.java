public class AddPart 
{
	String partName;
	String partCode;
	int partQuantity;
	
	String getPartName()
	{
		return partName;
	}
	String getPartCode()
	{
		return partCode;
	}
	int getPartQuantity()
	{
		return partQuantity;
	}

	void setPartName(String name)
	{
		partName = name;
	}
	void setPartCode(String code)
	{
		partCode = code;
	}
	void setPartQuantity(int qt)
	{
		partQuantity = qt;
	}
	
	AddPart(String partName, String partCode, int partQuantity)
	{
		setPartName(partName);
		setPartCode(partCode);
		setPartQuantity(partQuantity);
	}
}