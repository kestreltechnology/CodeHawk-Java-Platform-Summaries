| Method                                                                    | Timing (ns) |
| ------------------------------------------------------------------------- | ----------- |
| org.w3c.dom.NodeList.org.w3c.dom.Node item(int)                           | 50          |
| org.w3c.dom.NodeList.int getLength()                                      | 10          |
| java.util.TreeSet.int size()                                              | 170         |
| java.util.TreeSet.java.util.Iterator iterator()                           | 1500        |
| java.util.TreeSet.boolean isEmpty()                                       | 320         |
| java.util.TreeSet.boolean contains(java.lang.Object)                      | 1180        |
| java.util.TreeSet.java.lang.Object clone()                                | 132000      |
| java.util.TreeSet.boolean add(java.lang.Object) | 1000 + ((30  x  size(arg1))  +  size(this)) |
| java.util.TreeSet.<init>()                                                |  1350       |
| java.util.TreeMap.int size()                                              | 40          |
| java.util.TreeMap.java.lang.Object put(java.lang.Object,java.lang.Object) | 1000        |
| java.util.TreeMap.java.util.Set keySet()                                  | 380         |
| java.util.TreeMap.java.lang.Object get(java.lang.Object)                  | 980         |
| java.util.TreeMap.boolean containsKey(java.lang.Object)                   | 950         |
| java.util.TreeMap.<init>()                                                | 460         |
| java.util.logging.Level.boolean equals(java.lang.Object)                  | 12400       |
| java.util.LinkedList.int size()                                           | 40          |
| java.util.LinkedList.java.lang.Object removeFirst()                       | 370         |
| java.util.LinkedList.java.lang.Object getFirst()                          | 150         |
| java.util.LinkedList.java.lang.Object get(int)                  | 660  +  (10  x  arg1) |
| java.util.LinkedList.addFirst(java.lang.Object)                           | 670         |
| java.util.LinkedList.boolean add(java.lang.Object)                        | 700         |
| java.util.LinkedList.<init>()                                             | 760         |
| java.util.Hashtable.java.lang.String toString()                           | 500000      |
| java.util.Hashtable.int size()                                            | 225         |
| java.util.Hashtable.java.lang.Object put(java.lang.Object,java.lang.Object)   | 1000    |
| java.util.Hashtable.java.util.Set keySet()                                | 150         |
| java.util.Hashtable.java.lang.Object get(java.lang.Object)                | 400         |
| java.util.Hashtable.boolean containsKey(java.lang.Object)                 | 400         |
| java.util.Hashtable.<init>()                                              | 1150        |
| java.util.HashSet.int size()                                              | 160         |
| java.util.HashSet.java.util.Iterator iterator()                           | 2500        |
| java.util.HashSet.boolean isEmpty()                                       | 300         |
| java.util.HashSet.boolean contains(java.lang.Object)                      | 800         |
| java.util.HashSet.java.lang.Object clone()                                | 184000      |
| java.util.HashSet.boolean add(java.lang.Object)                           | 1530        |
| java.util.HashSet.<init>()                                                | 1100        |
| java.util.HashMap.int size()                                              | 40          |
| java.util.HashMap.java.lang.Object put(java.lang.Object,java.lang.Object) | 700  +  ((3  /  10)  x  size(arg1))
| java.util.HashMap.java.util.Set keySet()                                  | 190         |
| java.util.HashMap.java.lang.Object get(java.lang.Object)                  | 900         |
| java.util.HashMap.boolean containsKey(java.lang.Object)                   | 900         |
| java.util.HashMap.<init>()                                                | 480         |
| java.util.Arrays.fill(boolean[],boolean)           | 257  +  ((1  /  5)  x  size(arg1)) |
| java.util.ArrayList.int size()                                            | 30          |
| java.util.ArrayList.java.util.Iterator iterator()                         | 640         |
| java.util.ArrayList.java.lang.Object get(int)                             | 80          |
| java.util.ArrayList.<init>()                                              | 650         |
| java.time.LocalDateTime.java.time.LocalTime toLocalTime()                 | 4           |
| java.time.LocalDateTime.java.time.LocalDate toLocalDate()                 | 4           |
| java.net.InetSocketAddress.boolean equals(java.lang.Object)               | 493         |
| java.math.MathContext.<init>(int)                                         | 500         |
| java.math.BigInteger.java.math.BigInteger valueOf(long)                   | 2800        |
| java.math.BigInteger.java.lang.String toString(int)                       | 12000       |
| java.math.BigInteger.java.lang.String toString()                          | 12000       |
| java.math.BigInteger.byte[] toByteArray()                                 | 800         |
| java.math.BigInteger.boolean testBit(int)                                 | 350         |
| java.math.BigInteger.java.math.BigInteger subtract(java.math.BigInteger)  | 1340        |
| java.math.BigInteger.java.math.BigInteger shiftLeft(int)                  | 950         |
| java.math.BigInteger.java.math.BigInteger shiftRight(int)                 | 950         |
| java.math.BigInteger.java.math.BigInteger probablePrime(int,java.util.Random) | 3000000 |
| java.math.BigInteger.java.math.BigInteger multiply(java.math.BigInteger)  | 2100        |
| java.math.BigInteger.java.math.BigInteger mod(java.math.BigInteger)       | 3400        |
| java.math.BigInteger.int intValue()                                       | 350         |
| java.math.BigInteger.int hashCode()                                       | 180         |
| java.math.BigInteger.java.math.BigInteger gcd(java.math.BigInteger)       | 9000        |
| java.math.BigInteger.int compareTo(java.math.BigInteger)                  | 360         |
| java.math.BigInteger.int bitLength()                                      | 160         |
| java.math.BigInteger.java.math.BigInteger add(java.math.BigInteger)       | 1000        |
| java.math.BigInteger.<init>(java.lang.String,int)                         | 7100        |
| java.math.BigInteger.<init>(java.lang.String)                             | 7100        |
| java.math.BigInteger.<init>(int,java.util.Random)                         | 2700        |
| java.math.BigDecimal.java.lang.String toPlainString()                     | 3200        |
| java.math.BigDecimal.java.math.BigDecimal pow(int)                        | 5150        |
| java.math.BigDecimal.java.math.BigDecimal multiply(java.math.BigDecimal)  | 1000        |
| java.math.BigDecimal.java.math.BigDecimal multiply(java.math.BigDecimal,java.math.MathContext) | 1000 |
| java.math.BigDecimal.double doubleValue()                                 | 14000       |
| java.math.BigDecimal.int compareTo(java.math.BigDecimal)                  | 300         |
| java.math.BigDecimal.java.math.BigDecimal abs()                           | 350         |
| java.math.BigDecimal.java.math.BigDecimal add(java.math.BigDecimal)       | 600         |
| java.math.BigDecimal.<init>(java.lang.String)                             | 4000        |
| java.math.BigDecimal.<init>(int)                                          | 250         |
| java.math.BigDecimal.<init>(double)                                       | 700         |
| java.lang.UnsupportedOperationException.<init>(java.lang.String)          | 2500        |
| java.lang.Throwable.java.lang.Throwable fillInStackTrace()                | 2000        |
| java.lang.Throwable.<init>(java.lang.String,java.lang.Throwable)          | 2166        |
| java.lang.Throwable.<init>(java.lang.String)                              | 2158        |
| java.lang.Throwable.<init>()                                              | 2150        |
| java.lang.Thread.start()                                                  | 33000       |
| java.lang.Thread.sleep(long)                              | (1000000  x  localvar:var0) |
| java.lang.Thread.setPriority(int)                                         | 2600        |
| java.lang.Thread.join()                          | (100  +  symconst:ThreadJoin [0; ->) |
| java.lang.Thread.boolean isAlive()                                        | 800         |
| java.lang.Thread.interrupt()                                              | 1830        |
| java.lang.Thread.java.lang.ThreadGroup getThreadGroup()                   | 40          |
| java.lang.Thread.dumpStack()                                              | 469000      |
| java.lang.Thread.java.lang.Thread currentThread()                         | 130         |
| java.lang.Thread.<init>(java.lang.Runnable)                               | 9900        |
| java.lang.StringBuilder.java.lang.String toString()                       | 1000        |
| java.lang.StringBuilder.int length()                                      | 156         |
| java.lang.StringBuilder.java.lang.StringBuilder append(java.lang.String)  | 55          |
| java.lang.StringBuilder.java.lang.StringBuilder append(long)              | 1050        |
| java.lang.StringBuilder.java.lang.StringBuilder append(int)               | 1050        |
| java.lang.StringBuilder.java.lang.StringBuilder append(float)             | 620         |
| java.lang.StringBuilder.java.lang.StringBuilder append(double)            | 620         |
| java.lang.StringBuilder.java.lang.Appendable append(char)                 | 620         |
| java.lang.StringBuilder.java.lang.StringBuilder append(char)              | 600         |
| java.lang.StringBuilder.java.lang.StringBuilder append(boolean)           | 620         |
| java.lang.StringBuilder.<init>(java.lang.String)                          | 620         |
| java.lang.StringBuilder.<init>(int)                                       | 920         |
| java.lang.StringBuilder.<init>()                                          | 576         |
| java.lang.String.java.lang.String valueOf(int)                            | 1160        |
| java.lang.String.java.lang.String trim()                                  | 800         |
| java.lang.String.java.lang.String substring(int)                          | 1000        |
| java.lang.String.int length()                                             | 130         |
| java.lang.String.int lastIndexOf(java.lang.String)                        | 770         |
| java.lang.String.int lastIndexOf(int)                                     | 730         |
| java.lang.String.boolean isEmpty()                                        | 120         |
| java.lang.String.int indexOf(java.lang.String,int)                        | 1000        |
| java.lang.String.int indexOf(java.lang.String)                            | 1000        |
| java.lang.String.int indexOf(int,int)                                     | 550         |
| java.lang.String.int indexOf(int)                                         | 550         |
| java.lang.String.int hashCode()                                           | 140         |
| java.lang.String.byte[] getBytes()                                        | 6800        |
| java.lang.String.boolean equalsIgnoreCase(java.lang.String)               | 1100        |
| java.lang.String.boolean equals(java.lang.Object)                         | 145         |
| java.lang.String.boolean endsWith(java.lang.String)                       | 800         |
| java.lang.String.boolean contains(java.lang.CharSequence)                 | 3700        |
| java.lang.String.int compareToIgnoreCase(java.lang.String)                | 8500        |
| java.lang.String.int compareTo(java.lang.String)                          | 980         |
| java.lang.String.char charAt(int)                                         | 155         |
| java.lang.String.<init>(char[],int,int)                                   | 900         |
| java.lang.String.<init>(char[])                                           | 900         |
| java.lang.RuntimeException.<init>()                                       | 2178        |
| java.lang.Object.wait(long)                                               | (1000000  x  arg1 |
| java.lang.Object.<init>()                                                 | 143         |
| java.lang.NullPointerException.<init>(java.lang.String)                   | 2500        |
| java.lang.NullPointerException.<init>()                                   | 2192        |
| java.lang.Math.double sqrt(double)                                        | 15          |
| java.lang.Math.double sin(double)                                         | 330         |
| java.lang.Math.int round(float)                                           | 250         |
| java.lang.Math.long round(double)                                         | 280         |
| java.lang.Math.double random()                                            | 1660        |
| java.lang.Math.long min(long,long)                                        | 135         |
| java.lang.Math.int min(int,int)                                           | 150         |
| java.lang.Math.float min(float,float)                                     | 150         |
| java.lang.Math.long max(long,long)                                        | 135         |
| java.lang.Math.int max(int,int)                                           | 150         |
| java.lang.Math.double floor(double)                                       | 830         |
| java.lang.Math.double cos(double)                                         | 330         |
| java.lang.Math.double ceil(double)                                        | 900         |
| java.lang.Math.double atan2(double,double)                                | 380         |
| java.lang.Math.int abs(int)                                               | 130         |
| java.lang.Math.double abs(double)                                         | 16          |
| java.lang.Long.java.lang.Long valueOf(long)                               | 825         |
| java.lang.Long.long parseLong(java.lang.String,int)                       | 4900        |
| java.lang.Long.long longValue()                                           | 150         |
| java.lang.Long.boolean equals(java.lang.Object)                           | 180         |
| java.lang.Long.int compareTo(java.lang.Long)                              | 350         |
| java.lang.Integer.java.lang.Integer valueOf(java.lang.String)             | 2000        |
| java.lang.Integer.java.lang.Integer valueOf(int)                          | 600         |
| java.lang.Integer.java.lang.String toString(int,int)                      | 1300        |
| java.lang.Integer.java.lang.String toString(int)                          | 1000        |
| java.lang.Integer.java.lang.String toHexString(int)                       | 1200        |
| java.lang.Integer.java.lang.String toBinaryString(int)                    | 1100        |
| java.lang.Integer.int signum(int)                                         | 150         |
| java.lang.Integer.int parseInt(java.lang.String,int)                      | 2000        |
| java.lang.Integer.int parseInt(java.lang.String)                          | 2000        |
| java.lang.Integer.int numberOfLeadingZeros(int)                           | 170         |
| java.lang.Integer.int min(int,int)                                        | 310         |
| java.lang.Integer.int intValue()                                          | 40          |
| java.lang.Integer.boolean equals(java.lang.Object)                        | 160         |
| java.lang.Integer.int compareTo(java.lang.Integer)                        | 310         |
| java.lang.Integer.<init>(int)                                             | 470         |
| java.lang.IllegalStateException.<init>(java.lang.String)                  | 2500        |
| java.lang.IllegalArgumentException.<init>(java.lang.String)               | 2500        |
| java.lang.Float.java.lang.Float valueOf(float)                            | 630         |
| java.lang.Float.float parseFloat(java.lang.String)      | 3000  +  (150  x  size(arg1)) |
| java.lang.Float.boolean isNaN()                                           | 315         |
| java.lang.Float.boolean isInfinite()                                      | 315         |
| java.lang.Float.float floatValue()                                        | 150         |
| java.lang.Float.int floatToIntBits(float)                                 | 400         |
| java.lang.Exception.<init>()                                              | 2164        |
| java.lang.Error.<init>(java.lang.String)                                  | 2400        |
| java.lang.Double.java.lang.Double valueOf(java.lang.String)               | 3000        |
| java.lang.Double.java.lang.Double valueOf(double)                         | 630         |
| java.lang.Double.double parseDouble(java.lang.String)                     | 3000        |
| java.lang.Double.boolean isNaN()                                          | 330         |
| java.lang.Double.boolean isInfinite()                                     | 330         |
| java.lang.Double.int intValue()                                           | 150         |
| java.lang.Double.double doubleValue()                                     | 150         |
| java.lang.Double.long doubleToLongBits(double)                            | 220         |
| java.lang.Double.int compareTo(java.lang.Double)                          | 830         |
| java.lang.Character.java.lang.Character valueOf(char)                     | 150         |
| java.lang.Character.char toUpperCase(char)                                | 830         |
| java.lang.Character.java.lang.String toString(char)                       | 730         |
| java.lang.Character.char toLowerCase(char)                                | 830         |
| java.lang.Character.boolean isWhitespace(char)                            | 850         |
| java.lang.Character.boolean isUpperCase(char)                             | 1450        |
| java.lang.Character.boolean isLowerCase(char)                             | 1450        |
| java.lang.Character.boolean isDigit(char)                                 | 960         |
| java.lang.Character.char forDigit(int,int)                                | 150         |
| java.lang.Character.int digit(char,int)                                   | 820         |
| java.lang.Character.char charValue()                                      | 50          |
| java.lang.Character.<init>(char)                                          | 320         |
| java.lang.Byte.java.lang.Byte valueOf(byte)                               | 150         |
| java.lang.Byte.byte parseByte(java.lang.String)                           | 4000        |
| java.lang.Byte.byte byteValue()                                           | 45          |
| java.lang.Boolean.java.lang.Boolean valueOf(java.lang.String)             | 850         |
| java.lang.Boolean.java.lang.Boolean valueOf(boolean)                      | 150         |
| java.lang.Boolean.java.lang.String toString(boolean)                      | 160         |
| java.lang.Boolean.boolean parseBoolean(java.lang.String)                  | 680         |
| java.lang.Boolean.boolean equals(java.lang.Object)                        | 150         |
| java.lang.Boolean.boolean booleanValue()                                  | 50          |
| java.io.PrintStream.write(int)                                            | 11000       |
| java.io.PrintStream.write(byte[],int,int)                                 | 11300       |
| java.io.PrintStream.println(java.lang.String)                             | 23000       |
| java.io.PrintStream.println()                                             | 26750       |
| java.io.PrintStream.print(java.lang.String)                               | 4000        |
| java.io.PrintStream.flush()                                               | 880         |
| java.io.PrintStream.close()                                               | 16800       |
| java.io.FilterOutputStream.write(int)                                     | 8000        |
| java.io.FilterOutputStream.write(byte[],int,int)                          | 173000      |
| java.io.FilterOutputStream.write(byte[])                                  | 173000      |
| java.io.FilterOutputStream.flush()                                        | 510         |
| java.io.FilterOutputStream.close()                                        | 4370        |
| java.io.File.java.net.URI toURI()                                         | 95000       |
| java.io.File.java.nio.file.Path toPath()                                  | 5750        |
| java.io.File.boolean mkdir()                                              | 50000       |
| java.io.File.boolean isDirectory()                                        | 8500        |
| java.io.File.long getUsableSpace()                                        | 12000       |
| java.io.File.java.lang.String getPath()                                   | 50          |
| java.io.File.java.io.File getParentFile()                                 | 2400        |
| java.io.File.java.lang.String getName()                                   | 1800        |
| java.io.File.java.io.File getAbsoluteFile()                               | 8500        |
| java.io.File.boolean exists()                                             | 8000        |
| java.io.File.boolean equals(java.lang.Object)                             | 125         |
| java.io.File.deleteOnExit()                                               | 2200        |
| java.io.File.boolean delete()                                             | 27000       |
| java.io.File.boolean createNewFile()                                      | 44000       |
| java.io.File.boolean canWrite()                                           | 7000        |
| java.io.File.boolean canRead()                                            | 7000        |
| java.io.File.<init>(java.lang.String)                                     | 3000        |
| java.io.File.<init>(java.io.File,java.lang.String)                        | 8000        |
| java.io.ByteArrayOutputStream.write(int)                                  | 200         |
| java.io.ByteArrayOutputStream.write(byte[],int,int) | 250  +  ((size(arg1)  /  32)  x  size(arg1)) |
| java.io.ByteArrayOutputStream.byte[] toByteArray() | 350  +  ((13  /  50)  x  size(this)) |
| java.io.ByteArrayOutputStream.int size()                                  | 100         |
| java.io.ByteArrayOutputStream.close()                                     | 10          |
| java.io.ByteArrayOutputStream.<init>()                                    | 480         |
