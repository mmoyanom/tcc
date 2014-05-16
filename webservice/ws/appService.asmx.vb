Imports System.Web.Services
Imports System.Web.Services.Protocols
Imports System.ComponentModel
Imports ws.Service.Impl
Imports System.Web.Script.Serialization
Imports System.Web.Script.Services
Imports System.Xml.Serialization

' To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line.
' <System.Web.Script.Services.ScriptService()> _
<System.Web.Services.WebService(Namespace:="http://tempuri.org/")> _
<System.Web.Services.WebServiceBinding(ConformsTo:=WsiProfiles.BasicProfile1_1)> _
<ToolboxItem(False)> _
Public Class appService
    Inherits System.Web.Services.WebService

    <WebMethod()> _
    Public Function HelloWorld() As String
       Return "Hello World"
    End Function

    <WebMethod()> _
    <ScriptMethod(UseHttpGet:=True)> _
    Public Function ObterAlimentos() As String
        Dim response As String
        Dim service As New BMethods
        Dim x As New JsonObjectResponse
        Dim items As New List(Of Food)
        items = service.getFoods()
        If items IsNot Nothing Then
            Dim slz As New JavaScriptSerializer
            response = slz.Serialize(items)
            'x.value = response
        Else
            response = "Error"
        End If
        Return response
        'Return x
        'Return items
    End Function
    <WebMethod()> _
    <ScriptMethod(UseHttpGet:=True)> _
    Public Function CadastrarUsuario(ByVal Susu As String) As Integer
        Dim resul As Integer = 0
        Dim service As New BMethods
        Dim listusu As New List(Of User)
        If Susu IsNot Nothing Then
            Dim slz As New JavaScriptSerializer
            listusu = slz.Deserialize(Of List(Of User))(Susu)
            resul = service.insertUser(listusu)
        Else
            resul = 0
        End If
        Return resul
    End Function
    <WebMethod()> _
    <ScriptMethod(UseHttpGet:=True)> _
    Public Function AtualizarUsuario(ByVal Susu As String) As Integer
        Dim resul As Integer = 0
        Dim service As New BMethods
        Dim listusu As New List(Of User)
        If Susu IsNot Nothing Then
            Dim slz As New JavaScriptSerializer
            listusu = slz.Deserialize(Of List(Of User))(Susu)
            resul = service.updateUser(listusu)
        Else
            resul = 0
        End If
        Return resul
    End Function


End Class