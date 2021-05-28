'''
OS LAB #6 28/05/2021
2372 SEJAL KSHIRSAGAR
Title: Demonstration of Shell Script and shell script Menu-driven
program for Arithmetic operations (+,-,*,/)
'''

function accept(){
    echo -n "Enter 1st number: ";
    read num1;
    echo -n "Enter 2nd number: ";
    read num2;
}

until [[ $ch == 0 ]]; do
    
    echo "-----------------------------";
    echo "******** CALCULATOR *********";
    echo "0. Exit";
    echo "1. Addition";
    echo "2. Subtraction";
    echo "3. Multiplication";
    echo "4. Division";
    echo "5. Modulo Division";
    echo -n "Enter your choice: ";
    read ch;
    echo "-----------------------------";
    
    case $ch in
    "0") echo "******* PROGRAM END ********"
    ;;
    
    "1") accept;
         echo "Addition"
         echo "$num1 + $num2 = $(($num1 + $num2))"
    ;;
    
    "2") accept;
         echo "Subtraction";
         echo "$num1 - $num2 = $(($num1 - $num2))"
    ;;
    
    "3") accept;
         echo "Multiplication";
         echo "$num1 * $num2 = $(($num1 * $num2))"
    ;;
    
    "4") accept;
         echo "Division";
         echo "$num1 / $num2 = $(($num1 / $num2))"
    ;;
    
    "5") accept;
         echo "Modulo Division";
         echo "$num1 % $num2 = $(($num1 % $num2))"
    ;;
    esac
    
done



