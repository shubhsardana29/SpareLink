import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState, Suspense } from 'react';
import Spinner from '../components/Spinner/Spinner';

const API_BASE_URL = 'http://localhost:8080';

function fetchDataByRole(roleId) {
    let endpoint = '';
    if (roleId === 1) {
        endpoint = '/getalljobsheets';
    } else if (roleId === 2) {
        endpoint = '/getallpartrequests';
    }

    return axios.post(API_BASE_URL + endpoint, {
        roleId,
    });
}

function Dashboard() {
    const navigate = useNavigate();
    const [data, setData] = useState(null);
    const [isLoading, setIsLoading] = useState(true);
    const user = JSON.parse(localStorage.getItem('user'));
    const roleId = user?.role;

    useEffect(() => {
        if (roleId) {
            fetchDataByRole(Number(roleId))
                .then((response) => {
                    setData(response.data);
                })
                .catch((error) => {
                    console.error('Error:', error);
                    setData([]);
                })
                .finally(() => {
                    setIsLoading(false);
                });
        } else if (!roleId) {
            navigate('/');
        }
    }, [navigate, roleId]);

    return (
        <div className="bg-black h-full">
            <h2 className="text-center text-orange-400 font-bold text-xl">
                Dashboard
            </h2>
            <Suspense fallback={<Spinner />}>
                {isLoading ? (
                    <Spinner />
                ) : (
                    <div className="overflow-auto">
                        <h1 className="text-center text-slate-200">
                            Job Sheets
                        </h1>
                        <table className="w-full mt-2 text-white">
                            <thead className="bg-orange-400">
                                <tr>
                                    <th>Report ID</th>
                                    <th>Job Sheet ID</th>
                                    <th>Created At</th>
                                </tr>
                            </thead>
                            <tbody className="text-gray-400">
                                {data.map((item) => (
                                    <tr
                                        className="text-center"
                                        key={item.jobSheetID}
                                    >
                                        <td>{item.repairOrderID}</td>
                                        <td>{item.jobSheetID}</td>
                                        <td>
                                            {new Date(
                                                item.createdDate
                                            ).toLocaleDateString()}
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                )}
            </Suspense>
        </div>
    );
}

export default Dashboard;
