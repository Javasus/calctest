# calctest
консольное приложение “Калькулятор”. 

Приложение читает из консоли введенные пользователем строки, числа, арифметические операции 
проводимые между ними и выводит в консоль результат их выполнения.

Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b, a * b, a / b. Данные передаются в одну строку.

Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.
Калькулятор принимает на вход числа от 1 до 10 включительно, не более.
Калькулятор умеет работать только с целыми числами.
Калькулятор умеет работать только с арабскими или римскими цифрами одновременно, при вводе пользователем строки вроде 3 + II калькулятор выбросит исключение и прекратить свою работу.
При вводе римских чисел, ответ будет выведен римскими цифрами, соответственно, при вводе арабских - ответ выводится арабскими.
При вводе пользователем неподходящих чисел приложение выбрасывает исключение и завершает свою работу.
При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций, приложение выбрасывает исключение и завершает свою работу.
Результатом операции деления является целое число, остаток отбрасывается. 
Результатом работы калькулятора с арабскими числами могут быть отрицательные числа и ноль. Результатом работы калькулятора с римскими числами будут только положительные числа, если результат работы меньше единицы, выбрасывается исключение