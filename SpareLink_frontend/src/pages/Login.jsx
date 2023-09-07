import { useState , useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Spinner from '../components/Spinner/Spinner';
import image1 from '../assets/image1.png';
import image2 from '../assets/image2.png';
import image3 from '../assets/image3.png';

const API_URL = 'http://localhost:8080';

function Login() {
    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    const imageUrls = [image1, image2 , image3];

   
    const [currentImageIndex, setCurrentImageIndex] = useState(0);

    
    const goToNextImage = () => {
        const newIndex = (currentImageIndex + 1) % imageUrls.length;
        setCurrentImageIndex(newIndex);
    };

    
    useEffect(() => {
        const intervalId = setInterval(goToNextImage, 5000); 

        return () => clearInterval(intervalId);
    }, [currentImageIndex]);

    const handleFormSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);

        try {
            const response = await axios.post(API_URL + '/login', {
                username,
                password,
            });

            if (response.status === 200) {
                localStorage.setItem(
                    'user',
                    JSON.stringify(response.data.user)
                );
                navigate('/dashboard');
            } else {
                setError(response.data.message);
            }
        } catch (error) {
            console.error('Error:', error.message);
            setError('Incorrect username or password');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="flex justify-center items-center bg-black h-full">
            <div className="w-1/2 flex justify-center items-center">
                <img
                    src={imageUrls[currentImageIndex]}
                    alt="Carousel Images"
                    className="w-full"
                />
            </div>
            <div className="w-1/2 flex justify-center items-center">
                <div className="max-w-md mx-auto mt-8 p-6">
                    <h2 className="text-2xl font-bold mb-4 text-center">
                        Login
                    </h2>
                    <form onSubmit={handleFormSubmit}>
                        <div className="mb-4">
                            <label
                                htmlFor="username"
                                className="block text-gray-700"
                            >
                                Username:
                            </label>
                            <input
                                type="text"
                                id="username"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                                className="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring focus:border-orange-300"
                                required
                            />
                        </div>
                        <div className="mb-4">
                            <label
                                htmlFor="password"
                                className="block text-gray-700"
                            >
                                Password:
                            </label>
                            <input
                                type="password"
                                id="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                className="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring focus:border-orange-300"
                                required
                            />
                        </div>
                        <div className="flex justify-center">
                            <button
                                type="submit"
                                className="bg-orange-500 text-white px-4 py-2 rounded-md"
                                disabled={loading}
                            >
                                {loading ? <Spinner /> : 'Log In'}
                            </button>
                        </div>
                    </form>
                    {error && <p className="text-red-500 mt-2">{error}</p>}
                </div>
            </div>
        </div>
    );
}

export default Login;
