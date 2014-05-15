Imports ws.Connection
Imports System.Data.SqlClient


Namespace Service.Impl
    Public Class BMethods : Implements IBMethods

        Public Function generateReport(dtIni As Date, dtEnd As Date) As Integer Implements IBMethods.generateReport
            Return 1
        End Function

        Public Function getFoods() As List(Of Food) Implements IBMethods.getFoods
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
                        x.Weight = reader.GetDecimal(2)
                        x.Carbohydrate = reader.GetDecimal(3)
                        x.Fiber = reader.GetDecimal(4)
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

        Public Function insertLunch(food As List(Of Food)) As Integer Implements IBMethods.insertLunch
            Return 1
        End Function

        Public Function insertUser(lstuser As List(Of User)) As Integer Implements IBMethods.insertUser
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

        Public Function updateUser(lstuser As List(Of User)) As Integer Implements IBMethods.updateUser
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
    End Class
End Namespace

