all: compile run

compile:
	javac SAMReadLengthCalculator.java

run: compile
	java SAMReadLengthCalculator input.sam

clean:
	rm -f *.class

.PHONY: all compile run clean help