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

        Public Function insertLunchH(ByVal idUserBD As Integer,
                                     ByVal insulina As Double,
                                     ByVal glicemia As Double,
                                     ByVal carb As Double,
                                     ByVal fiber As Double) As Integer Implements IBMethods.insertLunchH

            Dim result As Integer = 0
            Dim con As SqlConnection = Nothing
            Try
                con = DBConnection.GetConnection
                con.Open()

                Dim cmd As New SqlCommand
                cmd.Connection = con
                cmd.CommandType = CommandType.StoredProcedure
                cmd.CommandText = ConfigurationManager.AppSettings("SP_REGISTRAR_DIARIO_H").ToString
                Dim dtTime As DateTime = DateTime.Now()

                cmd.Parameters.Add("@IDUSER", SqlDbType.Int).Value = idUserBD
                cmd.Parameters.Add("@INS", SqlDbType.Decimal).Value = insulina
                cmd.Parameters.Add("@GLI", SqlDbType.Decimal).Value = glicemia
                cmd.Parameters.Add("@CARB", SqlDbType.Decimal).Value = carb
                cmd.Parameters.Add("@FIBR", SqlDbType.Decimal).Value = fiber
                cmd.Parameters.Add("@FECHA", SqlDbType.DateTime).Value = dtTime
                cmd.Parameters.Add("@ID", SqlDbType.Int).Direction = ParameterDirection.Output
                cmd.ExecuteScalar()
                result = cmd.Parameters("@ID").Value


            Catch ex As Exception
                Console.WriteLine(ex.Message)
            Finally
                If con IsNot Nothing Then
                    con.Close()
                End If
            End Try
            Return result
        End Function

        Public Function insertLunchD(ByVal idH As Integer,
                                     ByVal idFood As Integer,
                                     ByVal weight As Double,
                                     ByVal carb As Double,
                                     ByVal fiber As Double) As Integer Implements IBMethods.insertLunchD

            Dim result As Integer = 0
            Dim con As SqlConnection = Nothing
            Try
                con = DBConnection.GetConnection
                con.Open()

                Dim cmd As New SqlCommand
                cmd.Connection = con
                cmd.CommandType = CommandType.StoredProcedure
                cmd.CommandText = ConfigurationManager.AppSettings("SP_REGISTRAR_DIARIO_D").ToString

                cmd.Parameters.Add("@IDH", SqlDbType.Int).Value = idH
                cmd.Parameters.Add("@IDFOOD", SqlDbType.Decimal).Value = idFood
                cmd.Parameters.Add("@WEI", SqlDbType.Decimal).Value = weight
                cmd.Parameters.Add("@CARB", SqlDbType.Decimal).Value = carb
                cmd.Parameters.Add("@FIBR", SqlDbType.Decimal).Value = fiber
                cmd.Parameters.Add("@ID", SqlDbType.Int).Direction = ParameterDirection.Output
                cmd.ExecuteScalar()
                result = cmd.Parameters("@ID").Value


            Catch ex As Exception
                Console.WriteLine(ex.Message)
            Finally
                If con IsNot Nothing Then
                    con.Close()
                End If
            End Try
            Return result
        End Function

        Public Function insertUser(ByVal name As String, ByVal email As String, ByVal weight As Double, size As Double, ByVal insulina As Double) As Integer Implements IBMethods.insertUser
            Dim result As Integer = 0
            Dim con As SqlConnection = Nothing
            Try
                con = DBConnection.GetConnection
                con.Open()

                Dim cmd As New SqlCommand
                cmd.Connection = con
                cmd.CommandType = CommandType.StoredProcedure
                cmd.CommandText = ConfigurationManager.AppSettings("SP_CADASTRAR_USUARIO").ToString

                cmd.Parameters.Add("@NAME", SqlDbType.VarChar).Value = name
                cmd.Parameters.Add("@WEIGHT", SqlDbType.Decimal).Value = weight
                cmd.Parameters.Add("@SIZE", SqlDbType.Decimal).Value = size
                cmd.Parameters.Add("@EMAIL", SqlDbType.VarChar).Value = email
                cmd.Parameters.Add("@INSULINA", SqlDbType.Decimal).Value = insulina
                cmd.Parameters.Add("@ID", SqlDbType.Int).Direction = ParameterDirection.Output
                cmd.ExecuteScalar()
                result = cmd.Parameters("@ID").Value


            Catch ex As Exception
                Console.WriteLine(ex.Message)
            Finally
                If con IsNot Nothing Then
                    con.Close()
                End If
            End Try
            Return result
        End Function

        Public Function updateUser(ByVal idDB As Integer,
                                     ByVal name As String,
                                     ByVal email As String,
                                     ByVal weight As Double,
                                     ByVal size As Double,
                                     ByVal insulina As Double) As Integer Implements IBMethods.updateUser
            Dim result As Integer
            Dim con As SqlConnection = Nothing
            Try
                con = DBConnection.GetConnection
                con.Open()

                Dim cmd As New SqlCommand
                cmd.Connection = con
                cmd.CommandType = CommandType.StoredProcedure
                cmd.CommandText = ConfigurationManager.AppSettings("SP_ATUALIZAR_USUARIO").ToString

                cmd.Parameters.Add("@ID", SqlDbType.Int).Value = idDB
                cmd.Parameters.Add("@NAME", SqlDbType.VarChar).Value = name
                cmd.Parameters.Add("@WEIGHT", SqlDbType.Decimal).Value = weight
                cmd.Parameters.Add("@SIZE", SqlDbType.Decimal).Value = size
                cmd.Parameters.Add("@EMAIL", SqlDbType.VarChar).Value = email
                cmd.Parameters.Add("@INSULINA", SqlDbType.Decimal).Value = insulina
                cmd.Parameters.Add("@IDUW", SqlDbType.Int).Direction = ParameterDirection.Output
                cmd.ExecuteScalar()
                result += cmd.Parameters("@IDUW").Value


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

