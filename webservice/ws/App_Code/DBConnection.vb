Imports Microsoft.VisualBasic
Imports System.Data.SqlClient
Imports System.Configuration

Public Class DBConnection

    Public Shared Function GetConnection() As SqlConnection

        Dim con As New SqlConnection
        con.ConnectionString = ConfigurationManager.ConnectionStrings("MSSQL_connectionString").ConnectionString
        Return con

    End Function


End Class


