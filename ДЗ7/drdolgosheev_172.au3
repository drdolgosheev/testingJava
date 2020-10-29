Run("calc.exe")
WinWaitActive("Калькулятор")
; Если поставить задержку меньше, мой комьютер не успевает занести число в буфера обмена => функция ClipGet возвращает 0
; Если ваш компьютер позволяет, то можно поставить время задержки < 100
AutoItSetOption("SendKeyDelay", 200)

TestBasic()
TestAllBasicOperations()
TestAdvancedOperations()
TestMemory()
TestProblemNumbers()
MsgBox(0, "INFO", "All test are finished")

Exit

; "Необычные" значения
Func TestProblemNumbers()
  ; Корень из 1/(-25)
  Send("{ESC}{ESC}")
  Send("-25")
  Send("{r}")
  Send("{ENTER}")
  Send("{@}")
  Send("{ENTER}")
  AssertEq(GetResult(), "Введены неверные данные", "TestProblemNumbers")

  ; Корень из 1/(-2)
  Send("{ESC}{ESC}")
  Send("-2")
  Send("{r}")
  Send("{ENTER}")
  Send("{@}")
  Send("{ENTER}")
  AssertEq(GetResult(), "Введены неверные данные", "TestProblemNumbers")

  ; Корень из -1
  Send("{ESC}{ESC}")
  Send("-1")
  Send("{ENTER}")
  Send("{@}")
  Send("{ENTER}")
  AssertEq(GetResult(), "Введены неверные данные", "TestProblemNumbers")

  ; 0 / n
  Send("{ESC}{ESC}")
  Send("0")
  Send("/")
  Send("1")
  Send("{ENTER}")
  AssertEq(GetResult(), 0, "TestProblemNumbers")

  Send("{ESC}{ESC}")
  Send("0")
  Send("/")
  Send("2")
  Send("{ENTER}")
  AssertEq(GetResult(), 0, "TestProblemNumbers")

  Send("{ESC}{ESC}")
  Send("0")
  Send("/")
  Send("4")
  Send("{ENTER}")
  AssertEq(GetResult(), 0, "TestProblemNumbers")

  ; 1/x для 0
  Send("{ESC}{ESC}")
  Send("0")
  Send("{r}")
  AssertEq(GetResult(), "Деление на ноль невозможно", "TestProblemNumbers")

  ; деление на 0
  Send("{ESC}{ESC}")
  Send("1")
  Send("/")
  Send("0")
  Send("{ENTER}")
  AssertEq(GetResult(), "Деление на ноль невозможно", "TestProblemNumbers")

  Send("{ESC}{ESC}")
  Send("55")
  Send("/")
  Send("0")
  Send("{ENTER}")
  AssertEq(GetResult(), "Деление на ноль невозможно", "TestProblemNumbers")

  ; Корень для отрицательных чисел
  Send("{ESC}{ESC}")
  Send("-")
  Send("1")
  Send("{ENTER}")
  Send("{@}")
  Send("{ENTER}")
  AssertEq(GetResult(), "Введены неверные данные", "TestProblemNumbers")

  Send("{ESC}{ESC}")
  Send("-")
  Send("10,55")
  Send("{ENTER}")
  Send("{@}")
  Send("{ENTER}")
  AssertEq(GetResult(), "Введены неверные данные", "TestProblemNumbers")

  MsgBox(0, "INFO", "TestProblemNumbers is finished")
EndFunc

; Базовые операции
Func TestBasic()
  TestBackspace()
  TestClearing()
  TestOverflow()
  MsgBox(0, "INFO", "TestBasic is finished")
EndFunc

 ; Более сложные операции: корень, проценты, 1/x
Func TestAdvancedOperations()
  TestInverse()
  TestInverseInverced()
  TestPercents()
  TestSqrt()
  TestSqrtMinus()
  MsgBox(0, "INFO", "TestAdvancedOperations is finished")
EndFunc

; Объединяем базовые операции int и float
Func TestAllBasicOperations()
  TestAllOperationsInt()
  TestAllOperationsFloat()
  MsgBox(0, "INFO", "TestAllBasicOperations is finished")
EndFunc

; Сложение, деление, вычитание и умножение для int
Func TestAllOperationsInt()
  TestIntAddition()
  TestIntSubstraction()
  TestIntDivision()
  TestIntMultiplication()
EndFunc

; Работа с памятью
Func TestMemory()
   Send("{ESC}{ESC}")
   ; Сохранили в память
   Send("10")
   Send("^m")
   ; Очистили поле воода
   Send("{ESC}")
   ; Достали из памяти
   Send("^r")
   Send("{*}")
   Send("10")
   Send("{ENTER}")
   AssertEq(GetResult(), 100, "TestMemory")

   Send("{ESC}")
   ; Почистили память
   Send("^l")
   Send("10")
   ; Запомнили 10
   Send("^m")
   Send("{ESC}")
   ; Прибавили 20
   Send("20")
   Send("^p")
   Send("{ESC}")
   ; Вычли 2
   Send("2")
   Send("^q")
   Send("{ESC}")
   ; Достали из памяти
   Send("^r")
   AssertEq(GetResult(), 28, "TestMemory")

   MsgBox(0, "INFO", "TestMemory is finished")
EndFunc

; Сложение, деление, вычитание и умножение для float
Func TestAllOperationsFloat()
  TestFloatAddition()
  TestFloatSubstraction()
  TestFloatDivision()
  TestFloatMultiplication()
EndFunc

; Проценты
Func TestPercents()
  TestPercentagesAddition()
  TestPercentagesMultiplication()
  TestPercentagesSubstraction()
  TestPercentagesDivision()
EndFunc

; BEGIN Базовые операции
Func TestBackspace()
   Send("{ESC}{ESC}")
   Send("1234567")
   Send("{BACKSPACE}")
   AssertEq(GetResult(), 123456, "TestBackspace")

   Send("{ESC}")
   Send("1234567")
   Send("{BACKSPACE}{BACKSPACE}{BACKSPACE}")
   AssertEq(GetResult(), 1234, "TestBackspace")
EndFunc

Func TestClearing()
   Send("{ESC}{ESC}")
   Send("1234567")
   Send("{ESC}")
   AssertEq(GetResult(), 0, "TestClearing")
EndFunc

Func TestInverse()
   Send("{ESC}{ESC}")
   Send("2")
   Send("{r}")
   Send("{ENTER}")
   AssertEq(GetResult(), 0.5, "TestInverse")

   Send("{ESC}")
   Send("1")
   Send("{r}")
   Send("{ENTER}")
   AssertEq(GetResult(), 1, "TestInverse")

   Send("{ESC}")
   Send("5")
   Send("{r}")
   Send("{ENTER}")
   AssertEq(GetResult(), 0.2, "TestInverse")

   Send("{ESC}")
   Send("8")
   Send("{r}")
   Send("{ENTER}")
   AssertEq(GetResult(), 0.125, "TestInverse")
EndFunc

Func TestInverseInverced()
   Send("{ESC}{ESC}")
   Send("2")
   Send("{r}")
   Send("{ENTER}")
   Send("{r}")
   Send("{ENTER}")
   AssertEq(GetResult(), 2, "TestInverseInverced")

   Send("{ESC}")
   Send("0,2")
   Send("{r}")
   Send("{ENTER}")
   AssertEq(GetResult(), 5, "TestInverseInverced")

   Send("{ESC}")
   Send("0,4")
   Send("{r}")
   Send("{ENTER}")
   AssertEq(GetResult(), 2.5, "TestInverseInverced")
EndFunc

Func TestOverflow()
   Send("{ESC}{ESC}")
   Send("9 999 999 999 999 999")
   Send("sq")
   Send("sq")
   Send("sq")
   Send("sq")
   Send("sq")
   Send("sq")
   Send("sq")
   Send("sq")
   Send("sq")
   Send("sq")
   AssertEq(GetResult(), "Переполнение", "TestOverflow")
EndFunc
; END Базовые операции

; BEGIN Сложение, деление, вычитание и умножение для int
Func TestIntAddition()
  Send("{ESC}{ESC}")
  Send("9")
  Send("{+}")
  Send("2")
  Send("{ENTER}")
  AssertEq(GetResult(), 11, "TestIntAddition")

  Send("{ESC}")
  Send("2")
  Send("{+}")
  Send("2")
  Send("{ENTER}")
  AssertEq(GetResult(), 4, "TestIntAddition")

  Send("{ESC}")
  Send("1")
  Send("{+}")
  Send("2")
  Send("{ENTER}")
  AssertEq(GetResult(), 3, "TestIntAddition")
EndFunc

Func TestIntSubstraction()
  Send("{ESC}{ESC}")
  Send("7")
  Send("{-}")
  Send("2")
  Send("{ENTER}")
  AssertEq(GetResult(), 5, "TestIntSubstraction")

  Send("{ESC}")
  Send("7")
  Send("{-}")
  Send("5")
  Send("{ENTER}")
  AssertEq(GetResult(), 2, "TestIntSubstraction")

  Send("{ESC}")
  Send("10")
  Send("{-}")
  Send("5")
  Send("{ENTER}")
  AssertEq(GetResult(), 5, "TestIntSubstraction")
EndFunc

Func TestIntDivision()
  Send("{ESC}{ESC}")
  Send("20")
  Send("{/}")
  Send("4")
  Send("{ENTER}")
  AssertEq(GetResult(), 5, "TestIntDivision")

  Send("{ESC}{ESC}")
  Send("1")
  Send("{/}")
  Send("2")
  Send("{ENTER}")
  AssertEq(GetResult(), 0.5, "TestIntDivision")

  Send("{ESC}{ESC}")
  Send("0")
  Send("{/}")
  Send("0")
  Send("{ENTER}")
  AssertEq(GetResult(), "Результат не определен", "TestIntDivision")
EndFunc

Func TestIntMultiplication()
  Send("{ESC}{ESC}")
  Send("2")
  Send("{*}")
  Send("3")
  Send("{ENTER}")
  AssertEq(GetResult(), 6, "TestIntMultiplication")

  Send("{ESC}")
  Send("5")
  Send("{*}")
  Send("5")
  Send("{ENTER}")
  AssertEq(GetResult(), 25, "TestIntMultiplication")

  Send("{ESC}")
  Send("4")
  Send("{*}")
  Send("4")
  Send("{ENTER}")
  AssertEq(GetResult(), 16, "TestIntMultiplication")
EndFunc
; END Сложение, деление, вычитание и умножение для int

; BEGIN Сложение, деление, вычитание и умножение для float
Func TestFloatAddition()
  Send("{ESC}{ESC}")
  Send("10,5")
  Send("{+}")
  Send("1,5")
  Send("{ENTER}")
  AssertEq(GetResult(), 12, "TestFloatAddition")
EndFunc

Func TestFloatSubstraction()
  Send("{ESC}{ESC}")
  Send("102,5")
  Send("{-}")
  Send("0,5")
  Send("{ENTER}")
  AssertEq(GetResult(), 102, "TestFloatSubstraction")
EndFunc

Func TestFloatDivision()
  Send("{ESC}{ESC}")
  Send("10,5")
  Send("{/}")
  Send("2,1")
  Send("{ENTER}")
  AssertEq(GetResult(), 5, "TestFloatDivision")
EndFunc

Func TestFloatMultiplication()
   Send("{ESC}{ESC}")
   Send("1,1")
   Send("{*}")
   Send("5,5")
   Send("{ENTER}")
   AssertEq(GetResult(), 6.05, "TestFloatMultiplication")
EndFunc
; END Сложение, деление, вычитание и умножение для float

; BEGIN Проценты
Func TestPercentagesAddition()
  Send("{ESC}{ESC}")
  Send("20")
  Send("{+}")
  Send("1%")
  Send("{ENTER}")
  AssertEq(GetResult(), 20.2, "TestPercentagesAddition")
EndFunc

Func TestPercentagesMultiplication()
  Send("{ESC}{ESC}")
  Send("10")
  Send("{*}")
  Send("20%")
  Send("{ENTER}")
  AssertEq(GetResult(), 2, "TestPercentagesMultiplication")
EndFunc

Func TestPercentagesSubstraction()
  Send("{ESC}{ESC}")
  Send("10")
  Send("{-}")
  Send("20%")
  Send("{ENTER}")
  AssertEq(GetResult(), 8, "TestPercentagesSubstraction")
EndFunc

Func TestPercentagesDivision()
  Send("{ESC}{ESC}")
  Send("10")
  Send("{/}")
  Send("20%")
  Send("{ENTER}")
  AssertEq(GetResult(), 50, "TestPercentagesDivision")
EndFunc
; END Проценты

; BEGIN Корень квадратный
Func TestSqrt()
  Send("{ESC}{ESC}")
  Send("100")
  Send("{@}")
  Send("{ENTER}")
  AssertEq(GetResult(), 10, "TestSqrtInt")

  Send("{ESC}{ESC}")
  Send("22,2")
  Send("{@}")
  Send("{ENTER}")
  AssertEq(GetResult(), 4.711687595755899, "TestSqrtFloat")
EndFunc

Func TestSqrtMinus()
  Send("{ESC}{ESC}")
  Send("0")
  Send("-")
  Send("100")
  Send("{ENTER}")
  Send("{@}")
  Send("{ENTER}")
  AssertEq(GetResult(), "Введены неверные данные", "TestSqrtInt")
EndFunc
; END Корень квадратный

Func GetResult()
	Send("^c")
	$str = StringReplace(ClipGet(), ",", ".")
	return Number($str)
 EndFunc

Func AssertEq($Result, $Expect, $Message)
   If Not ($Result = $Expect) Then
	  MsgBox(0, "Test is failed!", $Message & @LF & $Result & " is expected to be equal " & $Expect)
   EndIf
EndFunc


