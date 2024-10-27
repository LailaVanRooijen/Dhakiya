import { SidebarLayout } from "../../components/pageLayout/SidebarLayout";
import { Button } from "../../components/generic/Button";
import { LabelBar } from "../../components/generic/LabelBar";
import { useFetch } from "../../context/ContextApi";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { OverviewList } from "../../components/generic/OverviewList";

export const Environment: React.FC<EnvironmentProps> = () => {
    const { data: environments, error, fetchData } = useFetch();

    const addEnvironment = () => {
        console.log("add");
    };

    useEffect(() => {
        console.log("fething environments");
        fetchData("environments");
    }, []);

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div>
            <LabelBar label={"Environments"} />
            <SidebarLayout
                leftContent={
                    <>
                        {environments && (
                            <OverviewList
                                list={environments}
                                linkTo={"environment"}
                            />
                        )}
                    </>
                }
                rightContent={
                    <div>
                        <Button content="add +" handleClick={addEnvironment} />
                    </div>
                }
            />
        </div>
    );
};

/* interfaces */
interface EnvironmentProps {}

interface I_Environment {
    id: number;
    title: string;
}

/* css */
const listStyle = {
    listStyleType: "none",
};
