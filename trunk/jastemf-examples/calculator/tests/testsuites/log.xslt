<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes"/>
	
<xsl:variable name="idx" select="0"/>	
	
<xsl:template match="/">
	<html>
		
		<head>
        <title>Archive Log</title>

<script type="javascript">
<!--

function toggle(obj)
{
	obj.nextSibling.style.display = 'block';
}

-->
</script>


 




		</head>
		<body>
			<xsl:apply-templates/>
		</body>
	</html>
	</xsl:template>	
	
	<xsl:template match="archiveLog">
		<h1> Archive Log</h1>
		<table style="background-color:rgb(200,200,200)">
			<tbody>
				<tr>
					<th>Date</th>					
					<th>Type</th>
					<th>Message</th>
				</tr>
					<xsl:apply-templates/>
				
			</tbody>
		</table>
	</xsl:template>
	
	
	
	<xsl:template match="testrun[count(failed)=0]">
			<tr style="background-color:rgb(50,240,50)">
			<td><xsl:value-of select="@date" /></td>
			<td>R</td>

		
			<td>Test run (Configuration: <span style="font-weight:bold"> <xsl:value-of select="@configuration" /></span>)
			<xsl:call-template name="menu" />
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="testrun[count(failed)>0]">
		<tr style="background-color:rgb(240,50,50)">
			<td><xsl:value-of select="@date" /></td>
			<td>R</td>
			<td>Test run (Configuration: <span style="font-weight:bold"> <xsl:value-of select="@configuration" /></span>)
			<xsl:call-template name="menu" />
			
			</td>
		</tr>
	</xsl:template>

	
		<xsl:template match="failed">
		<tr style="background-color:rgb(240,100,100)">
			<td>
			Test [<span style="font-weight:bold"><xsl:value-of select="@test" /></span>] in Testsuit [<span style="font-weight:bold"><xsl:value-of select="@testsuit" /></span>] failed with reason: '<xsl:value-of select="@msg" />'
			
			<xsl:if test="string-length(@path)>0">
			<br />
			XPath: <i><xsl:value-of select="@path" /></i>
			</xsl:if>
			</td>
		</tr>
		
	</xsl:template>




 <xsl:template name="menu">

 	<xsl:if test="count(passed) > 0">
	<br /><a href="#" onclick="this.nextSibling.style.display = this.nextSibling.style.display=='none'?'block':'none'; return false;">passed tests</a>
		<table  style="display:none"><xsl:apply-templates select="passed"/></table>
	</xsl:if>
	<xsl:if test="count(skipped) > 0">
	<br /><a href="#" onclick="this.nextSibling.style.display = this.nextSibling.style.display=='none'?'block':'none'; return false;">skipped tests</a>
		<table  style="display:none"><xsl:apply-templates select="skipped"/></table>
	</xsl:if>
	<xsl:if test="count(failed) > 0">
	<br /><a href="#" onclick="this.nextSibling.style.display = this.nextSibling.style.display=='none'?'block':'none'; return false;">failed tests</a>
		<table  style="display:none"><xsl:apply-templates select="failed"/></table>
	</xsl:if>

 </xsl:template>




<xsl:template match="info">
		<tr style="background-color:rgb(240,240,240)">
			<td><xsl:value-of select="@date" /></td>
			<td>I</td>
			<td><xsl:value-of select="@msg" />
      <xsl:if test="count(testInformation) > 0">
        <br /><a href="#" onclick="this.nextSibling.style.display = this.nextSibling.style.display=='none'?'block':'none'; return false;">more Information</a>
  		  <table  style="display:none"><xsl:apply-templates select="testInformation"/></table>
  		</xsl:if>
      </td>
		</tr>
</xsl:template>
	




	<xsl:template match="passed">
		<tr style="background-color:rgb(50,240,50)">
			<td>
			Test [<span style="font-weight:bold"><xsl:value-of select="@test" /></span>] in Testsuit [<span style="font-weight:bold"><xsl:value-of select="@testSuite" /></span>] passed.
			</td>
		</tr>
		
	</xsl:template>
	
	
	<xsl:template match="skipped">
		<tr style="background-color:rgb(200,200,200)">
			<td>
			Test [<span style="font-weight:bold"><xsl:value-of select="@test" /></span>] in Testsuit [<span style="font-weight:bold"><xsl:value-of select="@testSuite" /></span>] skipped.
			</td>
		</tr>
		
	</xsl:template>
	
	
	
	<xsl:template match="testInformation">
		<tr style="background-color:rgb(200,200,200)">
			<td>
			  <b><xsl:value-of select="@test" /></b> 
			</td> 
      <td>
			  <b><xsl:value-of select="@msg" /></b> 
			</td>
		</tr>
		
	</xsl:template>
	
	
</xsl:stylesheet>
