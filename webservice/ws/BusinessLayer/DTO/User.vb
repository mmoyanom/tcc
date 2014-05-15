Public Class User

    Private _id As Integer
    Public Property Id() As Integer
        Get
            Return _id
        End Get
        Set(ByVal value As Integer)
            _id = value
        End Set
    End Property

    Private _name As Integer
    Public Property Name() As String
        Get
            Return _name
        End Get
        Set(ByVal value As String)
            _name = value
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

    Private _size As Double
    Public Property Size() As Double
        Get
            Return _size
        End Get
        Set(ByVal value As Double)
            _size = value
        End Set
    End Property

    Private _email As String
    Public Property Email() As String
        Get
            Return _email
        End Get
        Set(ByVal value As String)
            _email = value
        End Set
    End Property

    Private _insulina As Double
    Public Property Insulina() As Double
        Get
            Return _insulina
        End Get
        Set(ByVal value As Double)
            _insulina = value
        End Set
    End Property
End Class

