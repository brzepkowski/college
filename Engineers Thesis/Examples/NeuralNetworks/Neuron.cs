using System;

namespace RTadeusiewicz.NN.NeuralNetworks
{
    /// <summary>
    /// This class represents a linear neuron.
    /// </summary>
    /// <remarks>
    /// A neuron is the basic component of a neural network. It's analogous to the
    /// biological neuron. Its basic property is a set (or a vector, from the
    /// mathematical point of view) of the remembered weights. Each weight is a number
    /// assigned to a particular input, describing how strongly (positively or
    /// negatively) the signal given to that input influences the neuron's response.
    /// </remarks>
    public class Neuron
    {
        /// <summary>
        /// Creates a neuron containing the given number of inputs.
        /// </summary>
        /// <param name="inputCount">The number of neuron's inputs. The weights array
        /// will have the size equal to the given number of inputs.</param>
        public Neuron(int inputCount)
        {
            _weights = new double[inputCount];
            _prevWeights = new double[inputCount];
        }

        // Stores the value of the Weights property.
        private double[] _weights;

        /// Przechowuje poprzednie wartość własności Weights.
        private double[] _prevWeights;

        /// <summary>
        /// Returns an array containing the weights of each of this neuron's inputs.
        /// </summary>
        public double[] Weights
        {
            get
            {
                return _weights;
            }
        }

        /// <summary>
        /// Tablica zawierająca poprzednie wagi poszczególnych wejść neuronu.
        /// </summary>
        public double[] PrevWeights
        {
            get
            {
                return _prevWeights;
            }
        }

        /// <summary>
        /// Computes the response of this neuron to the given signal.
        /// <remarks>
        /// To compute the response of a linear network, we need to calculate the
        /// product of each input signal component and its corresponding input weight.
        /// The sum of these products is our response. In practice it turns out that
        /// the more the signal is "similar" to the weights remembered by the neuron,
        /// the more positive the neuron's response will be.
        /// </remarks>
        /// <param name="inputSignals">The signal provided to the neuron inputs. Each
        /// element of the array corresponds to an individual input, so the array's
        /// length should math the number of inputs.</param>
        /// <returns>The neuron's response to the given signal.</returns>
        public virtual double Response(double[] inputSignals)
        {
            // Check if the argument is correct.
            if (inputSignals == null || inputSignals.Length != _weights.Length)
                throw new ArgumentException(
                    "The signal array must have the same length as the weight array.");

            // Calculate the output as the sum of products.
            double result = 0.0;
            for (int i = 0; i < _weights.Length; i++)
                result += _weights[i] * inputSignals[i];
            return result;
        }

        // response neuronu dla exampla 10c white
        public virtual double Response(double[] inputSignals, out double odl)
        {
            // Sprawdzamy, czy argument jest poprawny.
            if (inputSignals == null || inputSignals.Length != _weights.Length)
                throw new ArgumentException(
                    "The signal array must have the same length as the weight array.");

            // Obliczamy wyjście.
            double result = 0.0;
            odl = 0;
            for (int i = 0; i < _weights.Length; i++)
                odl += Math.Pow(_weights[i] - inputSignals[i], 2);

            result = 1 / (1E-10 + odl);
            return result;
        }

        /// <summary>
        /// Oblicza moc podanego sygnału, używając zadanej miary.
        /// </summary>
        /// <param name="signals">Sygnał, którego moc obliczamy</param>
        /// <param name="norm">Norma, jaką mierzymy moc (euklidesowa lub manhattańska)
        /// </param>
        /// <returns>Obliczona moc</returns>
        /// <remarks>
        /// Moc sygnału wpływa na to, jak bardzo "istotny" jest on dla neuronu.
        /// Jeśli zamiast sygnału podamy wartości współczynników wag, dostaniemy
        /// moc śladu pamięciowego - czyli wartość określającą, jak bardzo
        /// "zdecydowany" jest dany neuron.
        /// </remarks>
        /// <seealso cref="MemoryTraceStrength(StrengthNorm)"/>
        public static double Strength(double[] signals, StrengthNorm norm)
        {
            double strength = 0;
            switch (norm)
            {
                case StrengthNorm.Manhattan:
                    foreach (double s in signals)
                        strength += Math.Abs(s);
                    return strength;

                case StrengthNorm.Euclidean:
                    foreach (double s in signals)
                        strength += s * s;
                    return Math.Sqrt(strength);

                default:
                    return 0.0;
            }
        }

        /// <summary>
        /// Oblicza moc śladu pamięciowego.
        /// </summary>
        /// <param name="norm">Norma, jaką mierzymy moc (euklidesowa lub manhattańska)
        /// </param>
        /// <returns>Obliczona moc śladu pamięciowego</returns>
        /// <remarks>
        /// Moc śladu pamięciowego określa, jak bardzo "zdecydowany" jest
        /// neuron. Im większa moc śladu pamięciowego, tym bardziej radykalne będą
        /// jego "opinie".
        /// </remarks>
        public double MemoryTraceStrength(StrengthNorm norm)
        {
            return Strength(_weights, norm);
        }

        /// <summary>
        /// Normalizuje podany sygnał.
        /// </summary>
        /// <param name="signals">Sygnał do normalizacji. Metoda zmienia zawartość
        /// tej tablicy, więc aby zachować oryginał, trzeba go najpierw gdzieś
        /// skopiować.</param>
        /// <remarks>
        /// Normalizacja polega na tym, że wszystkie składowe są zmieniane tak, aby
        /// nie uległy zmianie ich proporcje, za to moc sygnału wyniosła dokładnie 1.
        /// Innymi słowy, wektor sygnału jest skalowany tak, aby miał długość 1 (wg
        /// normy Euklidesowej).
        /// </remarks>
        public static void Normalize(double[] signals)
        {
            double strength = Strength(signals, StrengthNorm.Euclidean);
            for (int i = 0; i < signals.Length; i++)
                signals[i] /= strength;
        }

        /// <summary>
        /// Uczy neuron reakcji na dany sygnał. Jest to "uczenie z nauczycielem".
        /// </summary>
        /// <param name="signals">Sygnał, na który neuron ma zareagować.</param>
        /// <param name="expectedOutput">Prawidłowa odpowiedź neuronu.</param>
        /// <param name="ratio">Współczynnik uczenia. Im większy, tym szybszy będzie
        /// proces uczenia, lecz mniejsza jego stabilność.</param>
        /// <param name="previousResponse">Parametr wyjściowy; metoda umieszcza
        /// w nim odpowiedź neuronu na zadany sygnał przed uczeniem.</param>
        /// <param name="previousError">Parametr wyjściowy; metoda umieszcza w nim
        /// wielkość błędu popełnianego przez neuron przed uczeniem.</param>
        public void Learn(double[] signals, double expectedOutput, double ratio,
            out double previousResponse, out double previousError)
        {
            /* Nie sprawdzamy rozmiaru tablicy sygnałów - Response() zrobi to
             * za nas. */
            previousResponse = Response(signals);
            previousError = expectedOutput - previousResponse;
            for (int i = 0; i < _weights.Length; i++)
                _weights[i] += ratio * previousError * signals[i];
        }

        public void Learn(double[] signals, double[] previous_weights, double error, double sigma, double ratio, double momentum)
        {
            for (int i = 0; i < _weights.Length; i++)
                _weights[i] += ratio * sigma * signals[i] - momentum * (_weights[i] - previous_weights[i]);
        }

        public void Randomize(Random randomGenerator, double min, double max)
        {
            double length = max - min;
            for (int i = 0; i < _weights.Length; i++)
                _weights[i] = min + length * randomGenerator.NextDouble();
        }

        /* dodane dla przykladu 10a,ax,b */
        public void Learn(double[] signals, double etha, double max)
        {

            double previous_response = Response(signals);

            if (previous_response < 0.2 * max)
                previous_response *= 0.3;
            if (previous_response < 0)
                previous_response *= 0.1;

            for (int i = 0; i < _weights.Length; i++)
            {
                _prevWeights[i] = _weights[i];
                _weights[i] += etha * previous_response * (signals[i] - _weights[i]);
            }
        }

        /* dodane dla przykladu 10c samouczenie z konkurencja */
        public void LearnSelf(double[] signals, double etha)
        {
            for (int i = 0; i < _weights.Length; i++)
            {
                _prevWeights[i] = _weights[i];
                _weights[i] += etha * (signals[i] - _weights[i]);
            }
        }

        public void Randomize(Random randomGenerator, double min, double max, double epsilon)
        {
            double length = max - min;
            for (int i = 0; i < _weights.Length; i++)
            {
                _weights[i] = min + length * randomGenerator.NextDouble();
                if (Math.Abs(_weights[i]) < epsilon)
                    _weights[i] = epsilon;
            }
        }

    }

    /// <summary>
    /// Rodzaje miar mocy sygnału.
    /// </summary>
    public enum StrengthNorm
    { 

        /// <summary>
        /// Moc obliczana jako suma wartości bezwzględnych poszczególnych składników
        /// sygnału. Jej nazwa wzięła się stąd, że może ona służyć do określania
        /// długości, jaką należy przejechać w wielkim mieście między dwoma punktami,
        /// ale nie w linii prostej, tylko po prostopadłych do siebie ulicach.
        /// </summary>
        Manhattan,

        /// <summary>
        /// Moc obliczana jako pierwiastek kwadratowy sumy kwadratów poszczególnych
        /// składników sygnału. Innymi słowy - jest to odległość w przestrzeni między
        /// początkiem układu współrzędnych a punktem o współrzędnych odpowiadających
        /// składnikom sygnału.
        /// </summary>
        Euclidean
    }
}
