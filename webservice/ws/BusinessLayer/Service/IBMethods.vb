Namespace Service
    Public Interface IBMethods
        Function insertUser(ByVal lstuser As List(Of User)) As Integer
        Function updateUser(ByVal lstuser As List(Of User)) As Integer
        Function insertLunch(ByVal food As List(Of Food)) As Integer
        Function getFoods() As System.Collections.Generic.List(Of Food)
        Function generateReport(ByVal dtIni As Date, ByVal dtEnd As Date) As Integer
    End Interface
End Namespace

