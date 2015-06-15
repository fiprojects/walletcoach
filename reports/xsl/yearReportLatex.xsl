<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : yearReportLatex.xsl
    Created on : Nedeľa, 2015, júna 14, 18:27
    Author     : fajlo
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="text"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/report">
        \documentclass{article}
        \usepackage[utf8]{inputenc}

        \title{Report}
        \author{mail fajlo@users.noreply.github.com}
        \date{June 2015}

        \usepackage{fancyhdr}

        \pagestyle{fancy}
        \fancyhf{}
        \fancyhead[LE,RO]{Annual Report}
        \fancyhead[RE,LO]{WalletCoach}
        \fancyfoot[LE,RO]{\thepage}

        \begin{document}
        \section*{Annual Report for <xsl:value-of select="/report/@year"/>}

        \subsection*{Summary}
        \begin{center}
        \begin{tabular}{ | r | r | r | }
        \hline               

        \multicolumn{1}{|c|}{\bfseries Income} &amp;
        \multicolumn{1}{|c|}{\bfseries Expense} &amp;
        \multicolumn{1}{|c|}{\bfseries Total} \\
        \hline
        
        <xsl:value-of select="/report/summary/income"/>
        <xsl:text> CZK </xsl:text>&amp;
        <xsl:value-of select="/report/summary/expense"/>
        <xsl:text> CZK </xsl:text>&amp;
        <xsl:value-of select="/report/summary/total"/>
        <xsl:text> CZK </xsl:text>\\

        \hline  
        \end{tabular}
        \end{center}

        \subsection*{Report by Category}
        \begin{tabular}{ | l | l | l | l | }
        \hline               

        \bfseries Category &amp;
        \bfseries Income &amp;
        \bfseries Expense &amp;
        \bfseries Total \\
        \hline

        <xsl:apply-templates select="//category"/>
        
        \hline  
        \end{tabular}
        
        \subsection*{Report by Subject}
        \begin{tabular}{ | l | l | l | l | }
        \hline               

        \bfseries Subject &amp;
        \bfseries Income &amp;
        \bfseries Expense &amp;
        \bfseries Total \\
        \hline
          
        <xsl:apply-templates select="//subject"/>

        \hline  
        \end{tabular}

        \end{document}
        
    </xsl:template>
        
        <xsl:template match="//category">
            <xsl:text></xsl:text><xsl:value-of select="./@name"/> &amp;
            \multicolumn{1}{|r|}{<xsl:value-of select="./@income"/>} &amp;
            \multicolumn{1}{|r|}{<xsl:value-of select="./@expense"/>} &amp;
            \multicolumn{1}{|r|}{<xsl:value-of select="./@total"/>} \\
            \hline 
        </xsl:template>
          
        <xsl:template match="//subject">
            <xsl:text></xsl:text><xsl:value-of select="./@name"/> &amp;
            \multicolumn{1}{|r|}{<xsl:value-of select="./@income"/>} &amp;
            \multicolumn{1}{|r|}{<xsl:value-of select="./@expense"/>} &amp;
            \multicolumn{1}{|r|}{<xsl:value-of select="./@total"/>} \\
            \hline 
        </xsl:template>

</xsl:stylesheet>
