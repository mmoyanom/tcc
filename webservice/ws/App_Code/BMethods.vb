Imports Microsoft.VisualBasic
Imports System.Data.SqlClient
Imports System.Data

Imports User
Public Class BMethods

    Public Function insertUser(ByVal lstuser As List(Of User)) As Integer

        Dim result As Integer
        Dim con As SqlConnection = Nothing
        Try
            con = DBConnection.GetConnection
            con.Open()

            Dim cmd As New SqlCommand
            cmd.Connection = con
            cmd.CommandType = CommandType.StoredProcedure
            cmd.CommandText = ConfigurationManager.AppSettings("SP_CADASTRAR_USUARIO").ToString

            For Each u As User In lstuser
                cmd.Parameters.Add("@NAME", SqlDbType.VarChar).Value = u.Name
                cmd.Parameters.Add("@WEIGHT", SqlDbType.Decimal).Value = u.Weight
                cmd.Parameters.Add("@SIZE", SqlDbType.Decimal).Value = u.Size
                cmd.Parameters.Add("@EMAIL", SqlDbType.VarChar).Value = u.Email
                cmd.Parameters.Add("@INSULINA", SqlDbType.Decimal).Value = u.Insulina
                cmd.Parameters.Add("@ID", SqlDbType.Int).Direction = ParameterDirection.Output
                cmd.ExecuteScalar()
                result += cmd.Parameters("@ID").Value
            Next

        Catch ex As Exception
            Console.WriteLine(ex.Message)
        Finally
            If con IsNot Nothing Then
                con.Close()
            End If
        End Try
        Return result
    End Function

    Public Function updateUser(ByVal lstuser As List(Of User)) As Integer
        Dim result As Integer
        Dim con As SqlConnection = Nothing
        Try
            con = DBConnection.GetConnection
            con.Open()

            Dim cmd As New SqlCommand
            cmd.Connection = con
            cmd.CommandType = CommandType.StoredProcedure
            cmd.CommandText = ConfigurationManager.AppSettings("SP_ATUALIZAR_USUARIO").ToString

            For Each u As User In lstuser
                cmd.Parameters.Add("@ID", SqlDbType.Int).Value = u.Id
                cmd.Parameters.Add("@NAME", SqlDbType.VarChar).Value = u.Name
                cmd.Parameters.Add("@WEIGHT", SqlDbType.Decimal).Value = u.Weight
                cmd.Parameters.Add("@SIZE", SqlDbType.Decimal).Value = u.Size
                cmd.Parameters.Add("@EMAIL", SqlDbType.VarChar).Value = u.Email
                cmd.Parameters.Add("@INSULINA", SqlDbType.Decimal).Value = u.Insulina
                cmd.Parameters.Add("@IDUW", SqlDbType.Int).Direction = ParameterDirection.Output
                cmd.ExecuteScalar()
                result += cmd.Parameters("@IDUW").Value

            Next
            
        Catch ex As Exception
            Console.WriteLine(ex.Message)
        Finally
            If con IsNot Nothing Then
                con.Close()
            End If
        End Try
        Return result
    End Function

    Function insertLunch(ByVal food As List(Of Food)) As Integer
    End Function

    Public Function getFoods() As System.Collections.Generic.List(Of Food)
        Dim items As New List(Of Food)
        Dim con As SqlConnection = Nothing
        Try
            con = DBConnection.GetConnection
            con.Open()

            Dim cmd As New SqlCommand
            cmd.Connection = con
            cmd.CommandType = CommandType.StoredProcedure
            cmd.CommandText = ConfigurationManager.AppSettings("SP_OBTER_ALIMENTOS").ToString()

            Dim reader = cmd.ExecuteReader()
            If reader.HasRows Then
                While reader.Read()
                    Dim x As New Food
                    x.Id = reader.GetInt32(0)
                    x.Name = reader.GetString(1)
                    x.Weight = reader.GetDouble(2)
                    x.Carbohydrate = reader.GetDouble(3)
                    x.Fiber = reader.GetDouble(4)
                    items.Add(x)
                End While
            End If
        Catch ex As Exception
            items = Nothing
            Console.WriteLine(ex.Message)
        Finally
            If con IsNot Nothing Then
                con.Close()
            End If
        End Try
        Return items
    End Function

    Function generateReport(ByVal dtIni As Date, ByVal dtEnd As Date) As Integer
        Return 1
    End Function

End Class


