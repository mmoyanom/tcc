Public Class Food

    Private _id As Integer
    Public Property Id() As Integer
        Get
            Return _id
        End Get
        Set(ByVal value As Integer)
            _id = value
        End Set
    End Property

    Private _name As String
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

    Private _carb As Double
    Public Property Carbohydrate() As Double
        Get
            Return _carb
        End Get
        Set(ByVal value As Double)
            _carb = value
        End Set
    End Property

    Private _fiber As Double
    Public Property Fiber() As Double
        Get
            Return _fiber
        End Get
        Set(ByVal value As Double)
            _fiber = value
        End Set
    End Property

    'Private _usuCreate As String
    'Public Property UsuCreate() As String
    '    Get
    '        Return _usuCreate
    '    End Get
    '    Set(ByVal value As String)
    '        _usuCreate = value
    '    End Set
    'End Property

    'Private _dateCreate As Date
    'Public Property DateCreate() As Date
    '    Get
    '        Return _dateCreate
    '    End Get
    '    Set(ByVal value As Date)
    '        _dateCreate = value
    '    End Set
    'End Property

    'Private _usuUpdate As String
    'Public Property UsuUpdate() As String
    '    Get
    '        Return _usuUpdate
    '    End Get
    '    Set(ByVal value As String)
    '        _usuUpdate = value
    '    End Set
    'End Property

    'Private _dateUpdate As Date
    'Public Property DateUpdate() As Date
    '    Get
    '        Return _dateUpdate
    '    End Get
    '    Set(ByVal value As Date)
    '        _dateUpdate = value
    '    End Set
    'End Property
End Class

