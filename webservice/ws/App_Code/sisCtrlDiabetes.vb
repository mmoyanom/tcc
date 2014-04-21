Imports System.Web
Imports System.Web.Services
Imports System.Web.Services.Protocols

Imports System.ComponentModel
Imports System.Web.Script.Serialization
Imports System.Web.Script.Services
Imports System.Xml.Serialization

<WebService(Namespace:="http://tempuri.org/")> _
<WebServiceBinding(ConformsTo:=WsiProfiles.BasicProfile1_1)> _
<ToolboxItem(False)> _
Public Class sisCtrlDiabetes
    Inherits System.Web.Services.WebService

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

    <WebMethod()> _
    <ScriptMethod(UseHttpGet:=True)> _
    Public Function RegistrarDiario(Sfood As String) As Integer
        Dim resul As Integer = 0
        Dim service As New BMethods
        Dim listfood As New List(Of Food)
        If Sfood IsNot Nothing Then
            Dim slz As New JavaScriptSerializer
            listfood = slz.Deserialize(Of List(Of Food))(Sfood)
            resul = service.insertLunch(listfood)
        Else
            resul = 0
        End If
        Return resul
    End Function

    <WebMethod()> _
    <ScriptMethod(UseHttpGet:=True)> _
    Public Function ObterAlimentos() As JsonObjectResponse
        Dim response As String
        Dim service As New BMethods
        Dim x As New JsonObjectResponse
        Dim items As New List(Of Food)
        items = service.getFoods()
        If items IsNot Nothing Then
            Dim slz As New JavaScriptSerializer
            response = slz.Serialize(items)
            x.value = response
        Else
            response = "Error"
        End If

        Return x

    End Function

    <WebMethod()> _
    <ScriptMethod(UseHttpGet:=True)> _
    Public Function GerarRelatorio() As String
        Return "Hello World"
    End Function
End Class