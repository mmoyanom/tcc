Namespace Service
    Public Interface IBMethods
        Function insertUser(ByVal name As String, ByVal email As String, ByVal weight As Double, size As Double, ByVal insulina As Double) As Integer
        Function updateUser(ByVal idBD As Integer,
                                     ByVal name As String,
                                     ByVal email As String,
                                     ByVal weight As Double,
                                     ByVal size As Double,
                                     ByVal insulina As Double) As Integer
        Function insertLunchH(ByVal idUserBD As Integer,
                                     ByVal insulina As Double,
                                     ByVal glicemia As Double,
                                     ByVal carb As Double,
                                     ByVal fiber As Double) As Integer
        Function insertLunchD(ByVal idH As Integer,
                                     ByVal idFood As Integer,
                                     ByVal weight As Double,
                                     ByVal carb As Double,
                                     ByVal fiber As Double) As Integer
        Function getFoods() As System.Collections.Generic.List(Of Food)
        Function generateReport(ByVal dtIni As Date, ByVal dtEnd As Date) As Integer
    End Interface
End Namespace

