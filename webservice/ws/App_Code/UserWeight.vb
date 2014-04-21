Imports Microsoft.VisualBasic

Public Class UserWeight

    Private _idUsu As Integer
    Public Property IdUsu() As Integer
        Get
            Return _idUsu
        End Get
        Set(ByVal value As Integer)
            _idUsu = value
        End Set
    End Property

    Private _item As Integer
    Public Property Item() As Integer
        Get
            Return _item
        End Get
        Set(ByVal value As Integer)
            _item = value
        End Set
    End Property

    Private _dateReg As Date
    Public Property DateReg() As Date
        Get
            Return _dateReg
        End Get
        Set(ByVal value As Date)
            _dateReg = value
        End Set
    End Property

    Private _weight As Double
    Public Property Weight() As Double
        Get
            Return _weight
        End Get
        Set(ByVal value As Double)
            _weight = value
        End Set
    End Property

    Private _obs As String
    Public Property Obs() As String
        Get
            Return _obs
        End Get
        Set(ByVal value As String)
            _obs = value
        End Set
    End Property

End Class
