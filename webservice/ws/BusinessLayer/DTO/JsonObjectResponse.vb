Public Class JsonObjectResponse

    Private _value As String
    Public Property value() As String
        Get
            Return _value
        End Get
        Set(ByVal value As String)
            _value = value
        End Set
    End Property

End Class
